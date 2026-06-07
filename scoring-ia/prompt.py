def construire_prompt(candidature: dict, criteres: list) -> str:

    bareme_txt = ""
    for c in criteres:
        bareme_txt += f"\nCritere #{c['id']} — {c['libelle']} (priorite {c.get('priorite', 1)})\n"
        bareme_txt += f"  Type: {c['typeCalcul']}\n"
        bareme_txt += f"  Ponderation: {c['ponderation']}%  |  Bareme max: {c['baremeMax']} pts\n"

        if c['typeCalcul'] == 'BINAIRE':
            bareme_txt += f"  OUI = {c['pointsSiOui']} pts / NON = {c['pointsSiNon']} pts\n"
        elif c['typeCalcul'] == 'MULTIPLICATEUR':
            bareme_txt += f"  Formule: valeur x {c['pointsParUnite']}\n"
            if c.get('seuilMin') is not None:
                bareme_txt += f"  Seuil min: {c['seuilMin']}\n"
            if c.get('seuilMax') is not None:
                bareme_txt += f"  Seuil max: {c['seuilMax']}\n"
        elif c['typeCalcul'] == 'GRILLE':
            for t in c.get('grilles', []):
                bareme_txt += f"  [{t['min']} a {t['max']}] = {t['points']} pts\n"

    valeurs_txt = ""
    for critere_id, valeur in candidature.get('criteresValues', {}).items():
        valeurs_txt += f"  Critere #{critere_id} : {valeur}\n"

    return f"""Tu es un moteur de scoring RH. Calcule le score du candidat.
IMPORTANT : Tous les champs numeriques dans le JSON doivent etre des nombres calcules,
JAMAIS des expressions mathematiques comme (7*2.0)/10.0.
Fais le calcul toi-meme et ecris uniquement le resultat numerique.
Reponds UNIQUEMENT en JSON valide, sans texte avant ou apres, sans backticks.

=== BAREMES ===
{bareme_txt}

=== VALEURS DU CANDIDAT (ID: {candidature['candidatureId']}) ===
{valeurs_txt}

Regles de calcul (fais les calculs toi-meme) :
- BINAIRE       : OUI -> scoreObtenu = pointsSiOui, NON -> scoreObtenu = pointsSiNon
- MULTIPLICATEUR: scoreObtenu = min(valeur x pointsParUnite, baremeMax), respecte seuilMin/seuilMax
- GRILLE        : trouve la tranche [min,max] contenant la valeur -> scoreObtenu = points de la tranche
- scorePondere  : calcule (scoreObtenu / baremeMax) x ponderation et ecris le nombre final
- scoreTotal    : somme de tous les scoresPonderes, ecris le nombre final

Exemple correct   -> "scorePondere": 4.0
Exemple INTERDIT  -> "scorePondere": (7 * 2.0) / 10.0

Retourne ce JSON avec uniquement des nombres :
{{
  "candidatureId": {candidature['candidatureId']},
  "scoreTotal": <nombre calcule>,
  "raisonnement": "explication courte",
  "scores": [
    {{
      "idCritereScoring": <id>,
      "scoreObtenu": <nombre calcule>,
      "scorePondere": <nombre calcule>,
      "explication": "detail du calcul avec le resultat"
    }}
  ]
}}"""