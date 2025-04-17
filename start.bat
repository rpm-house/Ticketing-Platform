@echo off
echo Building project with Maven...
call mvn clean install -P prod

echo Building and starting Docker containers...
docker-compose up --build
echo Docker containers started.
pause
pause