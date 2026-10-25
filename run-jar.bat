@echo off
title SkillOps AI System - JAR Runner
echo ======================================================================
echo    Launching SkillOps via Executable JAR (dist\jar)
echo ======================================================================
call "%~dp0dist\jar\run-jar.bat" %*
