Write-Host "======================================================================" -ForegroundColor Cyan
Write-Host "   Starting SkillOps via Standalone Executable WAR" -ForegroundColor Green
Write-Host "======================================================================" -ForegroundColor Cyan
Write-Host " Package:      dist\war\skillops-0.0.1-SNAPSHOT.war"
Write-Host " Note:         Can also be dropped into Apache Tomcat webapps/"
Write-Host " Server Port:  8080"
Write-Host " Swagger UI:   http://localhost:8080/swagger-ui/index.html"
Write-Host " API Docs:     http://localhost:8080/v3/api-docs"
Write-Host " Health Check: http://localhost:8080/api/v1/health"
Write-Host "======================================================================" -ForegroundColor Cyan

$warPath = Join-Path $PSScriptRoot "skillops-0.0.1-SNAPSHOT.war"
if (-not (Test-Path $warPath)) {
    Write-Error "WAR file not found at: $warPath"
    exit 1
}

java -jar $warPath $args
