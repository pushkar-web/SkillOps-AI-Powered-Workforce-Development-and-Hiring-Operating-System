#!/bin/sh
# ============================================================
# render-start.sh — Render startup script
# ============================================================

set -e

# Target URL to inspect (check SPRING_DATASOURCE_URL or DATABASE_URL)
RAW_URL="${SPRING_DATASOURCE_URL:-$DATABASE_URL}"

if [ -n "$RAW_URL" ] && echo "$RAW_URL" | grep -qE '^postgres(ql)?://'; then
    # Extract host, port, db, query parameters
    CLEAN_HOST_PATH=$(echo "$RAW_URL" | sed 's|^postgres[a-z]*://[^@]*@||')

    # Ensure sslmode=require for Render PostgreSQL
    if ! echo "$CLEAN_HOST_PATH" | grep -q "sslmode="; then
        if echo "$CLEAN_HOST_PATH" | grep -q "?"; then
            CLEAN_HOST_PATH="${CLEAN_HOST_PATH}&sslmode=require"
        else
            CLEAN_HOST_PATH="${CLEAN_HOST_PATH}?sslmode=require"
        fi
    fi

    # Append all 10 domain schemas to currentSchema
    CLEAN_HOST_PATH="${CLEAN_HOST_PATH}&currentSchema=identity_authentication,user_profiles,skills,learning,projects,assessments,interviews,recruitment,notifications,analytics,public"

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

    # ── Check if tables are already initialized ──
    if command -v psql >/dev/null 2>&1; then
        echo "⏳ Checking database schemas..."
        if psql "$RAW_URL" -t -c "SELECT 1 FROM information_schema.tables WHERE table_name = 'roles' LIMIT 1;" 2>/dev/null | grep -q 1; then
            echo "✅ SkillOps database schemas already initialized."
        else
            echo "🌱 Initializing SkillOps database (first boot)..."
            psql "$RAW_URL" -f database/identity_authentication.sql || true
            psql "$RAW_URL" -f database/domain1_to_5_schemas.sql || true
            psql "$RAW_URL" -f database/domain6_to_10_schemas.sql || true
            psql "$RAW_URL" -f database/seed_large_dataset.sql || true
            echo "🎉 Database schemas and seed data ready!"
        fi
    fi
fi

APP_PORT="${PORT:-10000}"

echo "🚀 Starting SkillOps application on 0.0.0.0:${APP_PORT}..."

# Launch Spring Boot with explicit parameters
exec java $JAVA_OPTS \
    -Dserver.port="${APP_PORT}" \
    -Dserver.address=0.0.0.0 \
    -Dspring.datasource.url="${SPRING_DATASOURCE_URL}" \
    -Dspring.datasource.username="${SPRING_DATASOURCE_USERNAME}" \
    -Dspring.datasource.password="${SPRING_DATASOURCE_PASSWORD}" \
    -jar app.jar \
    --server.port="${APP_PORT}" \
    --server.address=0.0.0.0
