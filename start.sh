#!/bin/bash
mvn clean install -P prod
docker-compose up --build
