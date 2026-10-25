# SkillOps Standalone Executable JAR Distribution

This folder contains the self-contained, standalone Spring Boot executable JAR file for the **SkillOps AI-Powered Workforce Development & Hiring Operating System**.

## File Contents
- `skillops-0.0.1-SNAPSHOT.jar` (~58.8 MB): Fully packaged Spring Boot executable containing embedded Tomcat and all application dependencies.
- `run-jar.bat`: Double-click or run from command line to start the application on Windows.
- `run-jar.ps1`: PowerShell runner script.

## Quick Start

### Option 1: Direct Double-Click
Double-click `run-jar.bat` in this directory.

### Option 2: Terminal / PowerShell
```powershell
cd dist/jar
java -jar skillops-0.0.1-SNAPSHOT.jar
```

### Option 3: Custom Port or Profile
```powershell
# Change HTTP port to 9090
java -jar skillops-0.0.1-SNAPSHOT.jar --server.port=9090

# Custom database connection
java -jar skillops-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/skillops_db --spring.datasource.username=postgres --spring.datasource.password=postgres
```

## Endpoints Once Running
- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **Health Check**: [http://localhost:8080/api/v1/health](http://localhost:8080/api/v1/health)
- **Domain Summaries**: `http://localhost:8080/api/v1/{domain-name}/summary`
