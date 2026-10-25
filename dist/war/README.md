# SkillOps Executable & Deployable WAR Distribution

This folder contains the **Web Application Archive (WAR)** file for the **SkillOps AI-Powered Workforce Development & Hiring Operating System**.

## File Contents
- `skillops-0.0.1-SNAPSHOT.war` (~58.7 MB): Dual-purpose WAR file.
- `run-war.bat`: Double-click or run from command line to start the WAR directly using embedded Tomcat.
- `run-war.ps1`: PowerShell runner script.

## Dual Deployment Modes

### Mode 1: Run Standalone (No External Server Needed)
You can run this WAR directly with Java, just like a JAR:
```powershell
# Using the batch runner
run-war.bat

# Or terminal
java -jar skillops-0.0.1-SNAPSHOT.war
```

### Mode 2: Deploy to External Servlet Container (Apache Tomcat 10.1+ / 11)
SkillOps includes `SpringBootServletInitializer` in [`SkillOpsApplication.java`](../../src/main/java/com/skillops/SkillOpsApplication.java), making it 100% compatible with traditional servlet containers:

1. Copy `skillops-0.0.1-SNAPSHOT.war` (or rename to `ROOT.war` or `skillops.war`) to your Apache Tomcat installation directory:
   ```
   <TOMCAT_HOME>/webapps/skillops.war
   ```
2. Start Tomcat (`startup.bat` or `startup.sh`).
3. Tomcat will automatically unpack and deploy the application at:
   ```
   http://localhost:8080/skillops/
   http://localhost:8080/skillops/swagger-ui/index.html
   ```

## Endpoints Once Running (Standalone)
- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **Health Check**: [http://localhost:8080/api/v1/health](http://localhost:8080/api/v1/health)
- **Domain Summaries**: `http://localhost:8080/api/v1/{domain-name}/summary`
