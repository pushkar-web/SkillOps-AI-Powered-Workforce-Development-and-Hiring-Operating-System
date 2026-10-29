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

# Check if psql is installed
if (-not (Get-Command psql -ErrorAction SilentlyContinue)) {
    Write-Error "psql is not found in PATH. Please install PostgreSQL client or use Render's web shell."
    exit 1
}

Write-Host "`n[1/4] Applying Domain 1: Identity & Authentication schema..." -ForegroundColor Yellow
psql $DatabaseUrl -f "database/identity_authentication.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n[2/4] Applying Domains 1-5 schemas..." -ForegroundColor Yellow
psql $DatabaseUrl -f "database/domain1_to_5_schemas.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n[3/4] Applying Domains 6-10 schemas..." -ForegroundColor Yellow
psql $DatabaseUrl -f "database/domain6_to_10_schemas.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n[4/4] Seeding large dataset (62 tables across 10 schemas)..." -ForegroundColor Yellow
psql $DatabaseUrl -f "database/seed_large_dataset.sql"
Write-Host "  -> Done." -ForegroundColor Green

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host " All 62 tables and seed data created!" -ForegroundColor Green
Write-Host " SkillOps is ready on Render." -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan

