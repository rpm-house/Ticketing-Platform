@echo off
echo Stopping Python ML service...
docker-compose stop ml-model
echo Done.
pause