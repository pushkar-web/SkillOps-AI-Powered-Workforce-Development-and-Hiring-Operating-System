#!/bin/sh
# ============================================================
# init-render-db.sh
# Run this ONCE after Render PostgreSQL is provisioned
# to initialize the 62-table SkillOps schema and seed data.
#
# Usage:
#   1. Get your Render External Database URL from the Dashboard
#   2. Run: ./init-render-db.sh <EXTERNAL_DATABASE_URL>
#
# Example:
#   ./init-render-db.sh "postgres://skillops_user:abc123@dpg-xxx.oregon-postgres.render.com:5432/skillops"
# ============================================================

set -e

if [ -z "$1" ]; then
    echo "❌ Usage: ./init-render-db.sh <EXTERNAL_DATABASE_URL>"
    echo ""
    echo "   Get your External Database URL from:"
    echo "   Render Dashboard → skillops-db → Info → External Database URL"
    exit 1
fi

DATABASE_URL="$1"

echo "🗄️  SkillOps Database Initialization"
echo "======================================"

echo ""
echo "📋 Step 1/4: Creating identity & authentication schema..."
psql "$DATABASE_URL" -f database/identity_authentication.sql
echo "   ✅ Done"

echo ""
echo "📋 Step 2/4: Creating domains 1-5 schemas..."
psql "$DATABASE_URL" -f database/domain1_to_5_schemas.sql
echo "   ✅ Done"

echo ""
echo "📋 Step 3/4: Creating domains 6-10 schemas..."
psql "$DATABASE_URL" -f database/domain6_to_10_schemas.sql
echo "   ✅ Done"

echo ""
echo "📋 Step 4/4: Seeding large dataset (62 tables)..."
psql "$DATABASE_URL" -f database/seed_large_dataset.sql
echo "   ✅ Done"

echo ""
echo "🎉 SkillOps database fully initialized!"
echo "   62 tables across 10 domain schemas are ready."

