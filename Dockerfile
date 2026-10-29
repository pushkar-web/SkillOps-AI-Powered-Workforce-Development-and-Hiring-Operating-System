# ============================================================
# SkillOps — Multi-Stage Docker Build for Render Deployment
# Java 21 LTS + Spring Boot 3.4.5 + PostgreSQL
# ============================================================

# ── Stage 1: Build ──────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copy dependency descriptor first (Docker layer caching)
COPY pom.xml .

# Download dependencies (cached unless pom.xml changes)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the fat JAR (skip tests — they need a live database)
RUN mvn clean package -DskipTests -B \
    && mv build/skillops-*.jar build/app.jar

# ── Stage 2: Runtime ────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

# Install postgresql-client for automatic schema initialization on container boot
RUN apk add --no-cache postgresql-client

# Create non-root user for security
RUN addgroup -S skillops && adduser -S skillops -G skillops

# Copy built JAR from builder
COPY --from=builder /app/build/app.jar app.jar

# Copy database SQL scripts for optional initialization
COPY database/ ./database/

# Copy startup script
COPY render-start.sh ./render-start.sh
RUN sed -i 's/\r$//' render-start.sh && chmod +x render-start.sh

# Set ownership
RUN chown -R skillops:skillops /app

USER skillops

# Render injects PORT=10000 by default for web services
ENV PORT=10000
EXPOSE 10000 8080

# JVM tuning for containerized environments
ENV JAVA_OPTS="-XX:+UseContainerSupport \
  -XX:MaxRAMPercentage=75.0 \
  -XX:InitialRAMPercentage=50.0 \
  -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["./render-start.sh"]
