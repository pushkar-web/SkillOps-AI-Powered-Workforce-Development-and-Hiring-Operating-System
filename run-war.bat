@echo off
title SkillOps AI System - WAR Runner
echo ======================================================================
echo    Launching SkillOps via Executable WAR (dist\war)
echo ======================================================================
call "%~dp0dist\war\run-war.bat" %*
