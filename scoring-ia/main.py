from fastapi import FastAPI
from pydantic import BaseModel
from typing import Optional
import httpx, json, asyncio, logging, re, traceback

from prompt import construire_prompt

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

app = FastAPI(title="Scoring IA — Mutations")

OLLAMA_URL = "http://localhost:11434/api/chat"
MODELE     = "mistral"
SEMAPHORE  = asyncio.Semaphore(5)


# ── Modèles Pydantic ────────────────────────────────────────

class Candidature(BaseModel):
    candidatureId: int
    criteresValues: dict

class CritereScoring(BaseModel):
    id: int
    libelle: str
    typeCalcul: str
    ponderation: float
    baremeMax: float
    priorite: int = 1
    pointsSiOui: Optional[float] = None
    pointsSiNon: Optional[float] = None
    pointsParUnite: Optional[float] = None
    seuilMin: Optional[float] = None
    seuilMax: Optional[float] = None
    grilles: Optional[list] = []

class ScoringRequest(BaseModel):
    mouvementId: int
    nbPostes: int
    candidatures: list[Candidature]
    criteresScoring: list[CritereScoring]


# ── Appel Ollama ────────────────────────────────────────────
async def appeler_ollama(prompt: str, candidature_id: int) -> dict:
    async with SEMAPHORE:
        texte = None
        try:
            async with httpx.AsyncClient(timeout=None) as client:
                resp = await client.post(OLLAMA_URL, json={
                    "model": MODELE,
                    "stream": False,
                    "messages": [{"role": "user", "content": prompt}]
                })

                logger.info(f"Status HTTP: {resp.status_code}")
                logger.info(f"Reponse brute: {resp.text[:500]}")

                texte = resp.json()["message"]["content"].strip()
                logger.info(f"=== MISTRAL ===\n{texte}\n==============")

                # Nettoyer backticks
                if "```" in texte:
                    parties = texte.split("```")
                    for partie in parties:
                        p = partie.strip()
                        if p.startswith("json"):
                            texte = p[4:].strip()
                            break
                        elif p.startswith("{"):
                            texte = p
                            break

                # Remplacer expressions math par eval
                def evaluer_expression(match):
                    try:
                        return str(round(eval(match.group(0)), 4))
                    except:
                        return match.group(0)

                texte = re.sub(r'[\d\s\.\*\/\+\-\(\)]+(?=\s*[,\n\}])', evaluer_expression, texte)

                # Corriger JSON mal fermé
                texte = texte.strip()
                open_b  = texte.count('{')
                close_b = texte.count('}')
                if open_b > close_b:
                    texte += '}' * (open_b - close_b)

                # Corriger ] sans fermeture objet parent
                texte = re.sub(r'\]\s*\n\s*\}', ']}', texte)
                texte = re.sub(r'\]\s*\}', ']}', texte)

                logger.info(f"=== APRES NETTOYAGE ===\n{texte}\n======================")
                return json.loads(texte)

        except Exception as e:
            logger.error(f"Erreur candidature {candidature_id}: {e}")
            logger.error(traceback.format_exc())
            logger.error(f"Texte brut recu: {texte if texte else 'aucun'}")
            return None


# ── Endpoint scoring ────────────────────────────────────────

@app.post("/score")
async def scorer(request: ScoringRequest):
    # Dédupliquer les critères
    criteres = list({c.id: c.dict() for c in request.criteresScoring}.values())

    # Lancer tous les appels Ollama en parallèle
    tasks = [
        appeler_ollama(
            construire_prompt(cand.dict(), criteres),
            cand.candidatureId
        )
        for cand in request.candidatures
    ]
    resultats_bruts = await asyncio.gather(*tasks)

    # Séparer OK et erreurs
    ok      = [r for r in resultats_bruts if r is not None]
    erreurs = [
        request.candidatures[i].candidatureId
        for i, r in enumerate(resultats_bruts) if r is None
    ]

    # Tri : scoreTotal DESC + ex aequo par priorité critère
    criteres_tries = sorted(criteres, key=lambda c: c["priorite"])

    def cle_tri(r):
        scores_map = {s["idCritereScoring"]: s["scorePondere"] for s in r.get("scores", [])}
        return (-r["scoreTotal"],) + tuple(-scores_map.get(c["id"], 0) for c in criteres_tries)

    classement = sorted(ok, key=cle_tri)

    logger.info(f"Mouvement {request.mouvementId} — {len(ok)} scores calculés, {len(erreurs)} erreurs")

    return {
        "mouvementId":   request.mouvementId,
        "nbPostes":      request.nbPostes,
        "classement":    classement[:request.nbPostes],
        "horsSelection": classement[request.nbPostes:],
        "erreurs":       erreurs
    }


# ── Health check ────────────────────────────────────────────

@app.get("/health")
def health():
    return {"status": "ok", "modele": MODELE, "ollama": OLLAMA_URL}