# SkillOps — Verified Live API Directory (Render)

> **Deployment Host:** `https://skillops-api.onrender.com`  
> *(If your service slug on Render is `skillops-ai`, replace `skillops-api` with `skillops-ai` in the URLs below)*  
> **Status:** 🟢 Live  

---

## 🎯 Best Way to Explore & Test (Zero 404s)

👉 **Open the Interactive Swagger UI:**  
### **[https://skillops-api.onrender.com/swagger-ui.html](https://skillops-api.onrender.com/swagger-ui.html)**

> Inside Swagger UI, **every single endpoint is listed with its exact route**, parameters, and a **"Try it out"** button that runs directly against your live PostgreSQL database.

---

## 🌟 Core System Endpoints

| Endpoint | Direct Link | Expected Result |
|---|---|---|
| **Health Check** | [`/api/v1/health`](https://skillops-api.onrender.com/api/v1/health) | `200 OK` — `{"status":"UP", ...}` |
| **Swagger UI** | [`/swagger-ui.html`](https://skillops-api.onrender.com/swagger-ui.html) | Interactive API Explorer |
| **OpenAPI 3.0 Docs** | [`/v3/api-docs`](https://skillops-api.onrender.com/v3/api-docs) | Raw OpenAPI JSON spec |
| **Domain 1 Summary** | [`/api/v1/identity-domain/summary`](https://skillops-api.onrender.com/api/v1/identity-domain/summary) | Row counts of all tables in Domain 1 |
| **Domain 2 Summary** | [`/api/v1/profiles-domain/summary`](https://skillops-api.onrender.com/api/v1/profiles-domain/summary) | Row counts of all tables in Domain 2 |
| **Domain 3 Summary** | [`/api/v1/skills-domain/summary`](https://skillops-api.onrender.com/api/v1/skills-domain/summary) | Row counts of all tables in Domain 3 |
| **Domain 4 Summary** | [`/api/v1/learning-domain/summary`](https://skillops-api.onrender.com/api/v1/learning-domain/summary) | Row counts of all tables in Domain 4 |
| **Domain 5 Summary** | [`/api/v1/projects-domain/summary`](https://skillops-api.onrender.com/api/v1/projects-domain/summary) | Row counts of all tables in Domain 5 |
| **Domain 6 Summary** | [`/api/v1/assessments-domain/summary`](https://skillops-api.onrender.com/api/v1/assessments-domain/summary) | Row counts of all tables in Domain 6 |
| **Domain 7 Summary** | [`/api/v1/interviews-domain/summary`](https://skillops-api.onrender.com/api/v1/interviews-domain/summary) | Row counts of all tables in Domain 7 |
| **Domain 8 Summary** | [`/api/v1/recruitment-domain/summary`](https://skillops-api.onrender.com/api/v1/recruitment-domain/summary) | Row counts of all tables in Domain 8 |
| **Domain 9 Summary** | [`/api/v1/notifications-domain/summary`](https://skillops-api.onrender.com/api/v1/notifications-domain/summary) | Row counts of all tables in Domain 9 |
| **Domain 10 Summary** | [`/api/v1/analytics-domain/summary`](https://skillops-api.onrender.com/api/v1/analytics-domain/summary) | Row counts of all tables in Domain 10 |

---

## 📡 Exact Domain Endpoints (Accurate Controller Mappings)

> **Why you got 404 previously:** The controllers in Domains 2 through 10 use `-domain` prefixes (e.g., `/api/v1/profiles-domain/profiles`, NOT `/api/v1/profiles`). Below are the exact registered routes:

### 🏢 Domain 01: Identity & Authentication (`/api/v1/...`)
* **Users:** [https://skillops-api.onrender.com/api/v1/users](https://skillops-api.onrender.com/api/v1/users)
* **Roles:** [https://skillops-api.onrender.com/api/v1/roles](https://skillops-api.onrender.com/api/v1/roles)
* **Permissions:** [https://skillops-api.onrender.com/api/v1/permissions](https://skillops-api.onrender.com/api/v1/permissions)
* **Sessions:** [https://skillops-api.onrender.com/api/v1/sessions](https://skillops-api.onrender.com/api/v1/sessions)
* **Audit Logs:** [https://skillops-api.onrender.com/api/v1/audit-logs](https://skillops-api.onrender.com/api/v1/audit-logs)

### 👤 Domain 02: User Profiles (`/api/v1/profiles-domain/...`)
* **Profiles:** [https://skillops-api.onrender.com/api/v1/profiles-domain/profiles](https://skillops-api.onrender.com/api/v1/profiles-domain/profiles)
* **Resumes:** [https://skillops-api.onrender.com/api/v1/profiles-domain/resumes](https://skillops-api.onrender.com/api/v1/profiles-domain/resumes)
* **Education:** [https://skillops-api.onrender.com/api/v1/profiles-domain/educations](https://skillops-api.onrender.com/api/v1/profiles-domain/educations)
* **Experiences:** [https://skillops-api.onrender.com/api/v1/profiles-domain/experiences](https://skillops-api.onrender.com/api/v1/profiles-domain/experiences)
* **Certifications:** [https://skillops-api.onrender.com/api/v1/profiles-domain/profile-certifications](https://skillops-api.onrender.com/api/v1/profiles-domain/profile-certifications)
* **Career Goals:** [https://skillops-api.onrender.com/api/v1/profiles-domain/career-goals](https://skillops-api.onrender.com/api/v1/profiles-domain/career-goals)
* **Skill Passports:** [https://skillops-api.onrender.com/api/v1/profiles-domain/skill-passports](https://skillops-api.onrender.com/api/v1/profiles-domain/skill-passports)

### 🧠 Domain 03: Skills & Competency (`/api/v1/skills-domain/...`)
* **Skills:** [https://skillops-api.onrender.com/api/v1/skills-domain/skills](https://skillops-api.onrender.com/api/v1/skills-domain/skills)
* **Categories:** [https://skillops-api.onrender.com/api/v1/skills-domain/skill-categories](https://skillops-api.onrender.com/api/v1/skills-domain/skill-categories)
* **Competencies:** [https://skillops-api.onrender.com/api/v1/skills-domain/competencies](https://skillops-api.onrender.com/api/v1/skills-domain/competencies)
* **Skill Gaps:** [https://skillops-api.onrender.com/api/v1/skills-domain/skill-gaps](https://skillops-api.onrender.com/api/v1/skills-domain/skill-gaps)
* **Recommendations:** [https://skillops-api.onrender.com/api/v1/skills-domain/skill-recommendations](https://skillops-api.onrender.com/api/v1/skills-domain/skill-recommendations)

### 📚 Domain 04: Learning (`/api/v1/learning-domain/...`)
* **Courses:** [https://skillops-api.onrender.com/api/v1/learning-domain/courses](https://skillops-api.onrender.com/api/v1/learning-domain/courses)
* **Learning Paths:** [https://skillops-api.onrender.com/api/v1/learning-domain/learning-paths](https://skillops-api.onrender.com/api/v1/learning-domain/learning-paths)
* **Course Modules:** [https://skillops-api.onrender.com/api/v1/learning-domain/course-modules](https://skillops-api.onrender.com/api/v1/learning-domain/course-modules)
* **Lessons:** [https://skillops-api.onrender.com/api/v1/learning-domain/lessons](https://skillops-api.onrender.com/api/v1/learning-domain/lessons)
* **Enrollments:** [https://skillops-api.onrender.com/api/v1/learning-domain/enrollments](https://skillops-api.onrender.com/api/v1/learning-domain/enrollments)

### 💻 Domain 05: Projects (`/api/v1/projects-domain/...`)
* **Projects:** [https://skillops-api.onrender.com/api/v1/projects-domain/projects](https://skillops-api.onrender.com/api/v1/projects-domain/projects)
* **Milestones:** [https://skillops-api.onrender.com/api/v1/projects-domain/project-milestones](https://skillops-api.onrender.com/api/v1/projects-domain/project-milestones)
* **Submissions:** [https://skillops-api.onrender.com/api/v1/projects-domain/project-submissions](https://skillops-api.onrender.com/api/v1/projects-domain/project-submissions)
* **Reviews:** [https://skillops-api.onrender.com/api/v1/projects-domain/project-reviews](https://skillops-api.onrender.com/api/v1/projects-domain/project-reviews)

### 🎯 Domain 06: Assessments (`/api/v1/assessments-domain/...`)
* **Assessments:** [https://skillops-api.onrender.com/api/v1/assessments-domain/assessments](https://skillops-api.onrender.com/api/v1/assessments-domain/assessments)
* **Questions:** [https://skillops-api.onrender.com/api/v1/assessments-domain/assessment-questions](https://skillops-api.onrender.com/api/v1/assessments-domain/assessment-questions)
* **Coding Challenges:** [https://skillops-api.onrender.com/api/v1/assessments-domain/coding-challenges](https://skillops-api.onrender.com/api/v1/assessments-domain/coding-challenges)
* **Certificates:** [https://skillops-api.onrender.com/api/v1/assessments-domain/certificates](https://skillops-api.onrender.com/api/v1/assessments-domain/certificates)

### 🤖 Domain 07: Interviews (`/api/v1/interviews-domain/...`)
* **Interviews:** [https://skillops-api.onrender.com/api/v1/interviews-domain/interviews](https://skillops-api.onrender.com/api/v1/interviews-domain/interviews)
* **Rounds:** [https://skillops-api.onrender.com/api/v1/interviews-domain/interview-rounds](https://skillops-api.onrender.com/api/v1/interviews-domain/interview-rounds)
* **Scorecards:** [https://skillops-api.onrender.com/api/v1/interviews-domain/scorecards](https://skillops-api.onrender.com/api/v1/interviews-domain/scorecards)
* **AI Reports:** [https://skillops-api.onrender.com/api/v1/interviews-domain/ai-interview-reports](https://skillops-api.onrender.com/api/v1/interviews-domain/ai-interview-reports)

### 🤝 Domain 08: Recruitment (`/api/v1/recruitment-domain/...`)
* **Companies:** [https://skillops-api.onrender.com/api/v1/recruitment-domain/companies](https://skillops-api.onrender.com/api/v1/recruitment-domain/companies)
* **Jobs:** [https://skillops-api.onrender.com/api/v1/recruitment-domain/jobs](https://skillops-api.onrender.com/api/v1/recruitment-domain/jobs)
* **Applications:** [https://skillops-api.onrender.com/api/v1/recruitment-domain/applications](https://skillops-api.onrender.com/api/v1/recruitment-domain/applications)
* **Candidate Matches:** [https://skillops-api.onrender.com/api/v1/recruitment-domain/candidate-matches](https://skillops-api.onrender.com/api/v1/recruitment-domain/candidate-matches)

### 🔔 Domain 09: Notifications (`/api/v1/notifications-domain/...`)
* **Notifications:** [https://skillops-api.onrender.com/api/v1/notifications-domain/notifications](https://skillops-api.onrender.com/api/v1/notifications-domain/notifications)
* **Preferences:** [https://skillops-api.onrender.com/api/v1/notifications-domain/preferences](https://skillops-api.onrender.com/api/v1/notifications-domain/preferences)

### 📊 Domain 10: Analytics (`/api/v1/analytics-domain/...`)
* **Dashboards:** [https://skillops-api.onrender.com/api/v1/analytics-domain/dashboards](https://skillops-api.onrender.com/api/v1/analytics-domain/dashboards)
* **User Analytics:** [https://skillops-api.onrender.com/api/v1/analytics-domain/user-analytics](https://skillops-api.onrender.com/api/v1/analytics-domain/user-analytics)
