@echo off
echo Demarrage du microservice Scoring IA...
cd /d C:\pfe\scoring-ia
pip install -r requirements.txt
uvicorn main:app --host 0.0.0.0 --port 8000 --reload
pause