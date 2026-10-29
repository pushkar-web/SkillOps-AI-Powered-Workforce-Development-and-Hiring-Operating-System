#!/bin/sh
# ============================================================
# render-start.sh — Render startup script
# Converts Render's postgres:// URLs to Spring Boot JDBC format
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
fi

# Launch the Spring Boot application
exec java $JAVA_OPTS \
    -Dserver.port="${PORT:-8080}" \
    -jar app.jar
