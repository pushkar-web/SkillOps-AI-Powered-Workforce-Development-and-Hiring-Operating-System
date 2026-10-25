Write-Host "======================================================================" -ForegroundColor Cyan
Write-Host "   Building Both JAR and WAR Artifacts for SkillOps System" -ForegroundColor Green
Write-Host "======================================================================" -ForegroundColor Cyan

$scriptPath = Join-Path $PSScriptRoot "scripts\build_artifacts.py"
python $scriptPath $args
