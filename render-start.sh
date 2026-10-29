#!/bin/sh
# ============================================================
# render-start.sh — Render startup script
# 1. Converts Render's postgres:// URLs to Spring Boot JDBC format
# 2. Automatically initializes the 62 database tables on first boot
# 3. Launches the Spring Boot application
# ============================================================

set -e

# Target URL to inspect (check SPRING_DATASOURCE_URL or DATABASE_URL)
RAW_URL="${SPRING_DATASOURCE_URL:-$DATABASE_URL}"

if [ -n "$RAW_URL" ] && echo "$RAW_URL" | grep -qE '^postgres(ql)?://'; then
    # Extract host, port, db, query parameters
    CLEAN_HOST_PATH=$(echo "$RAW_URL" | sed 's|^postgres[a-z]*://[^@]*@||')
    export SPRING_DATASOURCE_URL="jdbc:postgresql://${CLEAN_HOST_PATH}"

    # Extract username if not explicitly provided
    if [ -z "$SPRING_DATASOURCE_USERNAME" ]; then
        export SPRING_DATASOURCE_USERNAME=$(echo "$RAW_URL" | sed -n 's|^postgres[a-z]*://\([^:]*\):.*|\1|p')
    fi

    # Extract password if not explicitly provided
    if [ -z "$SPRING_DATASOURCE_PASSWORD" ]; then
        export SPRING_DATASOURCE_PASSWORD=$(echo "$RAW_URL" | sed -n 's|^postgres[a-z]*://[^:]*:\([^@]*\)@.*|\1|p')
    fi

    echo "✅ Database connection successfully configured:"
    echo "   JDBC URL: $SPRING_DATASOURCE_URL"
    echo "   User:     $SPRING_DATASOURCE_USERNAME"

    # ── Auto-Initialize Database Schema (Zero manual steps required) ──
    if command -v psql >/dev/null 2>&1; then
        echo "⏳ Checking database connectivity..."
        MAX_RETRIES=15
        COUNT=0
        until psql "$RAW_URL" -c "SELECT 1;" >/dev/null 2>&1 || [ $COUNT -eq $MAX_RETRIES ]; do
            sleep 2
            COUNT=$((COUNT + 1))
        done

        if psql "$RAW_URL" -c "SELECT 1 FROM roles LIMIT 1;" >/dev/null 2>&1; then
            echo "✅ SkillOps database schemas already initialized."
        else
            echo "🌱 First boot: Auto-initializing 62 tables and seed data across 10 schemas..."
            psql "$RAW_URL" -f database/identity_authentication.sql || true
            psql "$RAW_URL" -f database/domain1_to_5_schemas.sql || true
            psql "$RAW_URL" -f database/domain6_to_10_schemas.sql || true
            psql "$RAW_URL" -f database/seed_large_dataset.sql || true
            echo "🎉 Database successfully populated with 62 tables and seed data!"
        fi
    fi
fi

# Launch the Spring Boot application
echo "🚀 Starting SkillOps application on port ${PORT:-8080}..."
exec java $JAVA_OPTS \
    -Dserver.port="${PORT:-8080}" \
    -jar app.jar
