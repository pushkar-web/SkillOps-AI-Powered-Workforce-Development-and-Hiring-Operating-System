Write-Host "======================================================================" -ForegroundColor Cyan
Write-Host "   Starting SkillOps via Standalone Executable JAR" -ForegroundColor Green
Write-Host "======================================================================" -ForegroundColor Cyan
Write-Host " Package:      dist\jar\skillops-0.0.1-SNAPSHOT.jar"
Write-Host " Server Port:  8080"
Write-Host " Swagger UI:   http://localhost:8080/swagger-ui/index.html"
Write-Host " API Docs:     http://localhost:8080/v3/api-docs"
Write-Host " Health Check: http://localhost:8080/api/v1/health"
Write-Host "======================================================================" -ForegroundColor Cyan

$jarPath = Join-Path $PSScriptRoot "skillops-0.0.1-SNAPSHOT.jar"
if (-not (Test-Path $jarPath)) {
    Write-Error "JAR file not found at: $jarPath"
    exit 1
}

java -jar $jarPath $args
