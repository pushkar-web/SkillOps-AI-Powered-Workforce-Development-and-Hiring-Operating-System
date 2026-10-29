# ============================================================
# init-render-db.ps1 (PowerShell version for Windows)
# Run this ONCE after your Render PostgreSQL database is provisioned.
#
# Usage:
#   .\init-render-db.ps1 "postgres://skillops_user:password@dpg-xxx.oregon-postgres.render.com:5432/skillops"
# ============================================================

param(
    [Parameter(Mandatory=$true, Position=0, HelpMessage="Render External Database URL")]
    [string]$DatabaseUrl
)

$ErrorActionPreference = "Stop"

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  SkillOps Database Initializer (Render)" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

# Resolve psql executable
$psqlCmd = "psql"
if (-not (Get-Command psql -ErrorAction SilentlyContinue)) {
    $commonPaths = @(
        "C:\Program Files\PostgreSQL\*\bin\psql.exe",
        "C:\Program Files (x86)\PostgreSQL\*\bin\psql.exe",
        "$env:LOCALAPPDATA\Programs\PostgreSQL\*\bin\psql.exe"
    )
    $found = Get-Item $commonPaths -ErrorAction SilentlyContinue | Select-Object -First 1
    if ($found) {
        $psqlCmd = $found.FullName
        Write-Host "Found psql at: $psqlCmd" -ForegroundColor Green
    } else {
        Write-Host "psql CLI is not installed locally on Windows." -ForegroundColor Red
        Write-Host "No worries! You can run this directly in Render's Web Shell (zero installation):" -ForegroundColor Cyan
        Write-Host "1. Go to Render Dashboard -> skillops-api -> Shell" -ForegroundColor White
        Write-Host "2. Run: psql `"$DatabaseUrl`" -f database/identity_authentication.sql" -ForegroundColor Yellow
        exit 1
    }
}

Write-Host "`n[1/4] Applying Domain 1: Identity & Authentication schema..." -ForegroundColor Yellow
& $psqlCmd $DatabaseUrl -f "database/identity_authentication.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n[2/4] Applying Domains 1-5 schemas..." -ForegroundColor Yellow
& $psqlCmd $DatabaseUrl -f "database/domain1_to_5_schemas.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n[3/4] Applying Domains 6-10 schemas..." -ForegroundColor Yellow
& $psqlCmd $DatabaseUrl -f "database/domain6_to_10_schemas.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n[4/4] Seeding large dataset (62 tables across 10 schemas)..." -ForegroundColor Yellow
& $psqlCmd $DatabaseUrl -f "database/seed_large_dataset.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host " All 62 tables and seed data created!" -ForegroundColor Green
Write-Host " SkillOps is ready on Render." -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan

