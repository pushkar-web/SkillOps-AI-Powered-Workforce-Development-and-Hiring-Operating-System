import os
import shutil
import subprocess
import sys
import re

ROOT_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
POM_PATH = os.path.join(ROOT_DIR, "pom.xml")
DIST_JAR = os.path.join(ROOT_DIR, "dist", "jar")
DIST_WAR = os.path.join(ROOT_DIR, "dist", "war")
TARGET_DIR = os.path.join(ROOT_DIR, "build")

os.makedirs(DIST_JAR, exist_ok=True)
os.makedirs(DIST_WAR, exist_ok=True)

import time

def run_command(cmd, max_retries=2):
    print(f"\n>>> Running: {cmd}")
    for attempt in range(1, max_retries + 1):
        res = subprocess.run(cmd, shell=True, cwd=ROOT_DIR)
        if res.returncode == 0:
            return
        if attempt < max_retries:
            print(f"[Warning] Command failed (attempt {attempt}). Waiting 3 seconds before retry...")
            time.sleep(3)
        else:
            print(f"Error executing command after {max_retries} attempts: {cmd}")
            sys.exit(res.returncode)

def set_packaging(packaging_type):
    with open(POM_PATH, "r", encoding="utf-8") as f:
        content = f.read()

    # Update packaging tag
    content = re.sub(r"<packaging>(jar|war)</packaging>", f"<packaging>{packaging_type}</packaging>", content)

    # Check tomcat dependency
    tomcat_dep = """        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>"""

    if packaging_type == "war":
        if "spring-boot-starter-tomcat" not in content:
            content = content.replace(
                "<groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>",
                "<groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n" + tomcat_dep
            )
    else:
        # For jar, remove provided tomcat so embedded tomcat is standard
        content = re.sub(r"\s*<dependency>\s*<groupId>org\.springframework\.boot</groupId>\s*<artifactId>spring-boot-starter-tomcat</artifactId>\s*<scope>provided</scope>\s*</dependency>", "", content)

    with open(POM_PATH, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Updated pom.xml -> packaging: {packaging_type}")

def build_jar():
    print("\n" + "="*60)
    print(" BUILDING EXECUTABLE JAR")
    print("="*60)
    set_packaging("jar")
    run_command("mvn package -DskipTests")
    src = os.path.join(TARGET_DIR, "skillops-0.0.1-SNAPSHOT.jar")
    dst = os.path.join(DIST_JAR, "skillops-0.0.1-SNAPSHOT.jar")
    shutil.copy2(src, dst)
    size_mb = os.path.getsize(dst) / (1024 * 1024)
    print(f"[OK] JAR packaged successfully: {dst} ({size_mb:.2f} MB)")

def build_war():
    print("\n" + "="*60)
    print(" BUILDING EXECUTABLE/DEPLOYABLE WAR")
    print("="*60)
    set_packaging("war")
    run_command("mvn package -DskipTests")
    src = os.path.join(TARGET_DIR, "skillops-0.0.1-SNAPSHOT.war")
    dst = os.path.join(DIST_WAR, "skillops-0.0.1-SNAPSHOT.war")
    shutil.copy2(src, dst)
    size_mb = os.path.getsize(dst) / (1024 * 1024)
    print(f"[OK] WAR packaged successfully: {dst} ({size_mb:.2f} MB)")

if __name__ == "__main__":
    mode = sys.argv[1].lower() if len(sys.argv) > 1 else "all"
    if mode in ("jar", "all"):
        build_jar()
    if mode in ("war", "all"):
        build_war()
    # Reset back to jar default
    set_packaging("jar")
    print("\n" + "="*60)
    print(" BUILD COMPLETE - BOTH ARTIFACTS READY IN dist/")
    print(f" JAR: {os.path.join(DIST_JAR, 'skillops-0.0.1-SNAPSHOT.jar')}")
    print(f" WAR: {os.path.join(DIST_WAR, 'skillops-0.0.1-SNAPSHOT.war')}")
    print("="*60)
