@echo off
echo Stopping and removing Docker containers...
docker-compose down --volumes --remove-orphans
Docker containers stopped and cleaned up.
pause