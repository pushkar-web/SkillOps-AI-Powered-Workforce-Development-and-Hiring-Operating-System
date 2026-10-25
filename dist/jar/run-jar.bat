@echo off
title SkillOps AI System - Standalone JAR Runner
echo ======================================================================
echo    Starting SkillOps via Standalone Executable JAR
echo ======================================================================
echo  Package:     dist\jar\skillops-0.0.1-SNAPSHOT.jar
echo  Server Port: 8080
echo  Swagger UI:  http://localhost:8080/swagger-ui/index.html
echo  API Docs:    http://localhost:8080/v3/api-docs
echo  Health Check:http://localhost:8080/api/v1/health
echo ======================================================================
java -jar "%~dp0skillops-0.0.1-SNAPSHOT.jar" %*
pause
