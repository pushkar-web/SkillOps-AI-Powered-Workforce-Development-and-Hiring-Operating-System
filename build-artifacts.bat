@echo off
title SkillOps AI System - Build All Artifacts (JAR & WAR)
echo ======================================================================
echo    Building Both JAR and WAR Artifacts for SkillOps System
echo ======================================================================
python "%~dp0scripts\build_artifacts.py" %*
pause
