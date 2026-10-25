import os
import sys
import json
import time
import base64
import subprocess
import requests
import websocket
from PIL import Image

MERMAID_CODE = """erDiagram
    USERS {
        bigint id PK
        bigint tenant_id
        varchar email UK
        varchar password_hash
        varchar phone
        varchar status
        boolean email_verified
        timestamptz last_login
        timestamptz created_at
        timestamptz updated_at
    }

    ROLES {
        bigint id PK
        varchar name UK
        varchar description
        timestamptz created_at
    }

    PERMISSIONS {
        bigint id PK
        varchar module
        varchar action
        varchar description
    }

    USER_ROLES {
        bigint id PK
        bigint user_id FK
        bigint role_id FK
        timestamptz assigned_at
    }

    SESSIONS {
        bigint id PK
        bigint user_id FK
        varchar access_token
        varchar refresh_token
        varchar ip_address
        varchar device
        timestamptz expires_at
    }

    REFRESH_TOKENS {
        bigint id PK
        bigint user_id FK
        varchar token UK
        timestamptz expiry_date
    }

    LOGIN_HISTORY {
        bigint id PK
        bigint user_id FK
        timestamptz login_time
        timestamptz logout_time
        varchar device
        varchar browser
        varchar ip_address
    }

    AUDIT_LOGS {
        bigint id PK
        bigint user_id FK
        varchar action
        varchar entity_name
        bigint entity_id
        text old_value
        text new_value
        timestamptz timestamp
    }

    PROFILES {
        bigint id PK
        bigint user_id FK
        varchar first_name
        varchar last_name
        date date_of_birth
        varchar gender
        varchar bio
        varchar profile_picture
        varchar city
        varchar state
        varchar country
        varchar linkedin_url
        varchar github_url
        timestamptz created_at
        timestamptz updated_at
    }

    RESUMES {
        bigint id PK
        bigint profile_id FK
        varchar file_name
        varchar file_url
        varchar file_type
        text parsed_text
        int version
        boolean is_primary
        timestamptz uploaded_at
    }

    EDUCATIONS {
        bigint id PK
        bigint profile_id FK
        varchar institute_name
        varchar degree
        varchar specialization
        numeric cgpa
        int passing_year
        int start_year
        int end_year
        boolean is_current
    }

    EXPERIENCES {
        bigint id PK
        bigint profile_id FK
        varchar company_name
        varchar designation
        varchar employment_type
        date start_date
        date end_date
        boolean currently_working
        varchar location
        text responsibilities
    }

    CERTIFICATIONS {
        bigint id PK
        bigint profile_id FK
        varchar certification_name
        varchar issuer
        varchar credential_id
        varchar credential_url
        date issue_date
        date expiry_date
    }

    CAREER_GOALS {
        bigint id PK
        bigint profile_id FK
        varchar target_role
        varchar target_industry
        varchar target_company
        varchar preferred_location
        numeric expected_ctc
        varchar employment_type
        int target_timeline_months
        timestamptz created_at
    }

    SKILL_PASSPORTS {
        bigint id PK
        bigint profile_id FK
        numeric passport_score
        int total_skills
        int verified_skills
        int completed_courses
        int completed_projects
        int certificates_earned
        varchar verification_status
        timestamptz last_updated
    }

    SKILL_CATEGORIES {
        bigint id PK
        varchar category_name UK
        varchar description
        varchar icon
        boolean is_active
        timestamptz created_at
    }

    SKILLS {
        bigint id PK
        bigint category_id FK
        varchar skill_name
        varchar description
        varchar difficulty_level
        boolean is_verified
        timestamptz created_at
    }

    SKILL_ASSESSMENTS {
        bigint id PK
        bigint skill_id FK
        varchar assessment_type
        int passing_score
        int total_marks
        int duration_minutes
    }

    COMPETENCIES {
        bigint id PK
        bigint skill_id FK
        varchar competency_name
        varchar description
        int display_order
    }

    COMPETENCY_LEVELS {
        bigint id PK
        bigint competency_id FK
        varchar level_name
        int level_value
        varchar criteria
    }

    USER_SKILLS {
        bigint id PK
        bigint profile_id FK
        bigint skill_id FK
        bigint competency_level_id FK
        bigint skill_assessment_id FK
        int proficiency_score
        boolean verified
        date last_used
        timestamptz updated_at
    }

    SKILL_GAPS {
        bigint id PK
        bigint profile_id FK
        bigint skill_id FK
        int current_level
        int target_level
        int gap_score
        varchar priority
    }

    SKILL_RECOMMENDATIONS {
        bigint id PK
        bigint profile_id FK
        bigint skill_id FK
        varchar recommendation_reason
        int recommendation_score
        varchar recommendation_type
        timestamptz generated_at
    }

    LEARNING_PATHS {
        bigint id PK
        bigint profile_id FK
        varchar title
        varchar description
        varchar difficulty
        int estimated_duration
        varchar status
        timestamptz created_at
    }

    COURSES {
        bigint id PK
        bigint learning_path_id FK
        varchar title
        varchar description
        varchar instructor
        int duration_hours
        varchar level
        boolean published
    }

    COURSE_MODULES {
        bigint id PK
        bigint course_id FK
        varchar title
        varchar description
        int sequence
    }

    LESSONS {
        bigint id PK
        bigint module_id FK
        varchar title
        varchar lesson_type
        int duration_minutes
        varchar video_url
        boolean downloadable
    }

    ASSIGNMENTS {
        bigint id PK
        bigint lesson_id FK
        varchar title
        varchar description
        date due_date
        int max_score
    }

    SUBMISSIONS {
        bigint id PK
        bigint assignment_id FK
        bigint profile_id FK
        varchar repository_url
        varchar file_url
        int score
        varchar status
        timestamptz submitted_at
    }

    QUIZZES {
        bigint id PK
        bigint lesson_id FK
        varchar title
        int total_marks
        int passing_marks
        int duration_minutes
    }

    QUIZ_ATTEMPTS {
        bigint id PK
        bigint quiz_id FK
        bigint profile_id FK
        int obtained_marks
        boolean passed
        int attempt_number
        timestamptz attempted_at
    }

    ENROLLMENTS {
        bigint id PK
        bigint profile_id FK
        bigint course_id FK
        date enrollment_date
        varchar status
        int completion_percentage
    }

    LEARNING_PROGRESS {
        bigint id PK
        bigint enrollment_id FK
        bigint lesson_id FK
        boolean completed
        int watch_percentage
        timestamptz completed_at
    }

    PROJECTS {
        bigint id PK
        bigint profile_id FK
        bigint course_id FK
        varchar title
        varchar description
        varchar project_type
        varchar difficulty_level
        varchar status
        date start_date
        date end_date
        varchar github_repository
        timestamptz created_at
    }

    PROJECT_MILESTONES {
        bigint id PK
        bigint project_id FK
        varchar title
        varchar description
        int sequence
        date due_date
        boolean completed
    }

    PROJECT_SUBMISSIONS {
        bigint id PK
        bigint project_id FK
        bigint profile_id FK
        varchar repository_url
        varchar live_demo_url
        varchar documentation_url
        varchar submission_status
        timestamptz submitted_at
    }

    PROJECT_REVIEWS {
        bigint id PK
        bigint submission_id FK
        bigint reviewer_id FK
        int technical_score
        int design_score
        int documentation_score
        int innovation_score
        int overall_score
        varchar feedback
        timestamptz reviewed_at
    }

    PORTFOLIO_ITEMS {
        bigint id PK
        bigint profile_id FK
        bigint project_id FK
        varchar title
        varchar thumbnail_uri
        varchar live_url
        varchar github_url
        boolean featured
        int views
        int likes
        timestamptz published_at
    }

    ASSESSMENTS {
        bigint id PK
        varchar title
        varchar assessment_type
        varchar difficulty
        int total_marks
        int duration_minutes
        int passing_marks
        boolean is_published
        timestamptz created_at
    }

    ASSESSMENT_QUESTIONS {
        bigint id PK
        bigint assessment_id FK
        varchar question_type
        varchar question
        int marks
        int sequence
    }

    CODING_CHALLENGES {
        bigint id PK
        bigint assessment_id FK
        varchar title
        varchar language
        varchar difficulty
        int max_score
    }

    ASSESSMENT_ATTEMPTS {
        bigint id PK
        bigint assessment_id FK
        bigint profile_id FK
        timestamptz started_at
        timestamptz submitted_at
        int obtained_marks
        boolean passed
        varchar status
    }

    CODING_SUBMISSIONS {
        bigint id PK
        bigint challenge_id FK
        bigint profile_id FK
        varchar repository_url
        varchar source_code_url
        int execution_score
        int quality_score
        timestamptz submitted_at
    }

    EVALUATION_RESULTS {
        bigint id PK
        bigint attempt_id FK
        bigint coding_submission_id FK
        int objective_score
        int coding_score
        int ai_score
        int final_score
        varchar remarks
    }

    CERTIFICATES {
        bigint id PK
        bigint assessment_id FK
        bigint profile_id FK
        varchar certificate_number UK
        varchar certificate_url
        date issued_on
        varchar verification_hash
    }

    ASSESSMENT_FEEDBACK {
        bigint id PK
        bigint evaluation_result_id FK
        varchar strengths
        varchar weaknesses
        varchar recommendations
        timestamptz generated_at
    }

    COMPANIES {
        bigint id PK
        varchar company_name
        varchar industry
        varchar website
        varchar headquarters
        int employee_count
        varchar company_type
        boolean verified
        timestamptz created_at
    }

    JOBS {
        bigint id PK
        bigint company_id FK
        varchar job_title
        varchar department
        varchar employment_type
        varchar work_mode
        varchar location
        numeric salary_min
        numeric salary_max
        varchar experience_level
        date application_deadline
        varchar status
    }

    JOB_REQUIREMENTS {
        bigint id PK
        bigint job_id FK
        varchar requirement_type
        varchar description
        boolean mandatory
        int priority
    }

    APPLICATIONS {
        bigint id PK
        bigint job_id FK
        bigint profile_id FK
        bigint resume_id FK
        date applied_on
        varchar application_status
        int ai_match_score
        boolean shortlisted
    }

    CANDIDATE_MATCHES {
        bigint id PK
        bigint application_id FK
        int skill_score
        int project_score
        int assessment_score
        int interview_score
        int overall_score
        varchar recommendation
        timestamptz generated_at
    }

    OFFERS {
        bigint id PK
        bigint application_id FK
        varchar designation
        numeric offered_ctc
        date joining_date
        date offer_expiry
        varchar offer_status
    }

    HIRING_STATUS {
        bigint id PK
        bigint application_id FK
        varchar current_stage
        boolean background_verification
        boolean documents_verified
        boolean onboarding_completed
        timestamptz updated_at
    }

    INTERVIEWS {
        bigint id PK
        bigint application_id FK
        bigint candidate_profile_id FK
        varchar interview_type
        varchar mode
        date scheduled_date
        time scheduled_time
        int duration_minutes
        varchar status
        varchar meeting_link
        timestamptz created_at
    }

    INTERVIEW_ROUNDS {
        bigint id PK
        bigint interview_id FK
        varchar round_name
        varchar round_type
        int round_number
        varchar interviewer
        int duration_minutes
        varchar status
    }

    INTERVIEW_FEEDBACK {
        bigint id PK
        bigint round_id FK
        bigint reviewer_id FK
        int communication_rating
        int technical_rating
        int problem_solving_rating
        int confidence_rating
        varchar strengths
        varchar improvements
        varchar remarks
        timestamptz submitted_at
    }

    INTERVIEW_SCORECARDS {
        bigint id PK
        bigint interview_id FK
        int communication_score
        int technical_score
        int aptitude_score
        int behavioral_score
        int coding_score
        int final_score
        varchar recommendation
        timestamptz generated_at
    }

    AI_INTERVIEW_REPORTS {
        bigint id PK
        bigint interview_id FK
        bigint scorecard_id FK
        numeric confidence_score
        varchar strengths
        varchar weaknesses
        varchar improvement_areas
        varchar suggested_learning_path
        varchar ai_summary
        timestamptz generated_at
    }

    NOTIFICATION_PREFERENCES {
        bigint id PK
        bigint user_id FK
        boolean email_enabled
        boolean push_enabled
        boolean sms_enabled
        boolean whatsapp_enabled
        boolean in_app_enabled
        boolean marketing_enabled
        boolean reminder_enabled
        boolean system_alerts_enabled
        varchar preferred_language
        varchar timezone
        timestamptz updated_at
    }

    NOTIFICATIONS {
        bigint id PK
        bigint user_id FK
        bigint preference_id FK
        varchar title
        varchar message
        varchar notification_type
        varchar delivery_channel
        varchar priority
        varchar status
        varchar reference_module
        bigint reference_id
        boolean is_read
        timestamptz scheduled_at
        timestamptz sent_at
        timestamptz created_at
    }

    DASHBOARDS {
        bigint id PK
        bigint profile_id FK
        varchar dashboard_type
        varchar dashboard_name
        int widgets_count
        boolean is_default
        timestamptz last_refreshed
        timestamptz created_at
    }

    USER_ANALYTICS {
        bigint id PK
        bigint dashboard_id FK
        bigint profile_id FK
        int learning_score
        int skill_score
        int assessment_score
        int project_score
        int interview_score
        int employability_score
        int profile_completion
        int total_courses
        int completed_courses
        int total_projects
        int completed_projects
        int certificates_earned
        int applications_submitted
        int interviews_completed
        int offers_received
        int streak_days
        timestamptz updated_at
    }

    USERS ||--o{ USER_ROLES : "has roles"
    ROLES ||--o{ USER_ROLES : "assigned to"
    USERS ||--o{ SESSIONS : "starts"
    USERS ||--o{ REFRESH_TOKENS : "owns"
    USERS ||--o{ LOGIN_HISTORY : "logs"
    USERS ||--o{ AUDIT_LOGS : "triggers"
    USERS ||--|| PROFILES : "has profile (1:1)"
    USERS ||--|| NOTIFICATION_PREFERENCES : "configures (1:1)"
    USERS ||--o{ NOTIFICATIONS : "receives"
    NOTIFICATION_PREFERENCES ||--o{ NOTIFICATIONS : "governs"

    PROFILES ||--o{ RESUMES : "uploads"
    PROFILES ||--o{ EDUCATIONS : "includes"
    PROFILES ||--o{ EXPERIENCES : "includes"
    PROFILES ||--o{ CERTIFICATIONS : "holds"
    PROFILES ||--o{ CAREER_GOALS : "targets"
    PROFILES ||--|| SKILL_PASSPORTS : "possesses (1:1)"

    SKILL_CATEGORIES ||--o{ SKILLS : "categorizes"
    SKILLS ||--o{ SKILL_ASSESSMENTS : "has assessments"
    SKILLS ||--o{ COMPETENCIES : "composed of"
    COMPETENCIES ||--o{ COMPETENCY_LEVELS : "defines"
    PROFILES ||--o{ USER_SKILLS : "mastered by"
    SKILLS ||--o{ USER_SKILLS : "assigned in"
    COMPETENCY_LEVELS ||--o{ USER_SKILLS : "rated at"
    SKILL_ASSESSMENTS ||--o{ USER_SKILLS : "validated via"
    PROFILES ||--o{ SKILL_GAPS : "identified for"
    SKILLS ||--o{ SKILL_GAPS : "gap in"
    PROFILES ||--o{ SKILL_RECOMMENDATIONS : "tailored for"
    SKILLS ||--o{ SKILL_RECOMMENDATIONS : "recommends"

    PROFILES ||--o{ LEARNING_PATHS : "follows"
    LEARNING_PATHS ||--o{ COURSES : "comprises"
    COURSES ||--o{ COURSE_MODULES : "divided into"
    COURSE_MODULES ||--o{ LESSONS : "contains"
    LESSONS ||--o{ ASSIGNMENTS : "assigns"
    LESSONS ||--o{ QUIZZES : "tests with"
    ASSIGNMENTS ||--o{ SUBMISSIONS : "receives"
    PROFILES ||--o{ SUBMISSIONS : "submits"
    QUIZZES ||--o{ QUIZ_ATTEMPTS : "records"
    PROFILES ||--o{ QUIZ_ATTEMPTS : "takes"
    PROFILES ||--o{ ENROLLMENTS : "enrolls"
    COURSES ||--o{ ENROLLMENTS : "has students"
    ENROLLMENTS ||--o{ LEARNING_PROGRESS : "tracks"
    LESSONS ||--o{ LEARNING_PROGRESS : "progress on"

    PROFILES ||--o{ PROJECTS : "develops"
    COURSES ||--o{ PROJECTS : "associated with"
    PROJECTS ||--o{ PROJECT_MILESTONES : "broken into"
    PROJECTS ||--o{ PROJECT_SUBMISSIONS : "submitted under"
    PROFILES ||--o{ PROJECT_SUBMISSIONS : "submits"
    PROJECT_SUBMISSIONS ||--o{ PROJECT_REVIEWS : "reviewed by"
    PROFILES ||--o{ PROJECT_REVIEWS : "reviews as mentor"
    PROFILES ||--o{ PORTFOLIO_ITEMS : "showcases"
    PROJECTS ||--o{ PORTFOLIO_ITEMS : "featured in"

    ASSESSMENTS ||--o{ ASSESSMENT_QUESTIONS : "consists of"
    ASSESSMENTS ||--o{ CODING_CHALLENGES : "includes"
    ASSESSMENTS ||--o{ ASSESSMENT_ATTEMPTS : "attempted via"
    PROFILES ||--o{ ASSESSMENT_ATTEMPTS : "participates in"
    CODING_CHALLENGES ||--o{ CODING_SUBMISSIONS : "evaluated via"
    PROFILES ||--o{ CODING_SUBMISSIONS : "submits code"
    ASSESSMENT_ATTEMPTS ||--o{ EVALUATION_RESULTS : "graded into"
    CODING_SUBMISSIONS ||--o{ EVALUATION_RESULTS : "scored into"
    EVALUATION_RESULTS ||--o{ ASSESSMENT_FEEDBACK : "generates feedback"
    ASSESSMENTS ||--o{ CERTIFICATES : "awards"
    PROFILES ||--o{ CERTIFICATES : "earns"

    COMPANIES ||--o{ JOBS : "publishes"
    JOBS ||--o{ JOB_REQUIREMENTS : "demands"
    JOBS ||--o{ APPLICATIONS : "receives"
    PROFILES ||--o{ APPLICATIONS : "applies to"
    RESUMES ||--o{ APPLICATIONS : "attached in"
    APPLICATIONS ||--|| CANDIDATE_MATCHES : "scored by AI (1:1)"
    APPLICATIONS ||--o{ OFFERS : "granted"
    APPLICATIONS ||--|| HIRING_STATUS : "current stage (1:1)"

    APPLICATIONS ||--o{ INTERVIEWS : "schedules"
    PROFILES ||--o{ INTERVIEWS : "interviewee"
    INTERVIEWS ||--o{ INTERVIEW_ROUNDS : "conducted in"
    INTERVIEW_ROUNDS ||--o{ INTERVIEW_FEEDBACK : "evaluated in"
    PROFILES ||--o{ INTERVIEW_FEEDBACK : "reviewed by interviewer"
    INTERVIEWS ||--|| INTERVIEW_SCORECARDS : "summarized in (1:1)"
    INTERVIEWS ||--|| AI_INTERVIEW_REPORTS : "analyzed by AI (1:1)"
    INTERVIEW_SCORECARDS ||--o{ AI_INTERVIEW_REPORTS : "contextualizes"

    PROFILES ||--o{ DASHBOARDS : "views"
    DASHBOARDS ||--o{ USER_ANALYTICS : "displays"
    PROFILES ||--o{ USER_ANALYTICS : "metrics of"
"""

HTML_TEMPLATE = """<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SkillOps - Complete Unified ER Diagram (All 10 Domains)</title>
    <style>
        body {{
            margin: 0;
            padding: 40px;
            background: #ffffff;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
            color: #1e293b;
        }}
        .header {{
            text-align: center;
            margin-bottom: 30px;
        }}
        .header h1 {{
            font-size: 32px;
            margin: 0 0 10px 0;
            color: #0f172a;
            font-weight: 800;
            letter-spacing: -0.5px;
        }}
        .header p {{
            font-size: 16px;
            color: #64748b;
            margin: 0;
        }}
        #diagram-container {{
            display: flex;
            justify-content: center;
            align-items: center;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
            padding: 40px;
        }}
        svg {{
            max-width: none !important;
            height: auto !important;
        }}
    </style>
    <script src="mermaid.min.js"></script>
</head>
<body>
    <div class="header">
        <h1>SkillOps Platform - Unified Enterprise ER Diagram</h1>
        <p>Complete 10-Domain Architecture | 62 Relational Entities | Spring Boot 3.4.5 & PostgreSQL 15+</p>
    </div>
    <div id="diagram-container">
        <pre class="mermaid" id="er-mermaid">
{mermaid_code}
        </pre>
    </div>
    <script>
        mermaid.initialize({{
            startOnLoad: false,
            theme: 'default',
            themeVariables: {{
                fontFamily: 'Inter, Segoe UI, sans-serif',
                fontSize: '13px',
                primaryColor: '#e0e7ff',
                primaryTextColor: '#1e1b4b',
                primaryBorderColor: '#6366f1',
                lineColor: '#4f46e5',
                secondaryColor: '#f1f5f9',
                tertiaryColor: '#f8fafc'
            }},
            er: {{
                useMaxWidth: false,
                diagramPadding: 20,
                layoutDirection: 'TB',
                minEntityWidth: 100,
                minEntityHeight: 75,
                entityPadding: 15,
                stroke: '#475569',
                fill: '#f8fafc',
                fontSize: 12
            }}
        }});

        window.renderComplete = false;
        window.renderError = null;

        mermaid.run({{
            querySelector: '.mermaid'
        }}).then(() => {{
            window.renderComplete = true;
            console.log('Mermaid ER Diagram Rendered Successfully');
        }}).catch(err => {{
            window.renderComplete = true;
            window.renderError = err.toString();
            console.error('Mermaid render error:', err);
        }});
    </script>
</body>
</html>
"""

def generate():
    os.makedirs('docs', exist_ok=True)
    html_path = os.path.abspath(os.path.join('docs', 'skillops_overall_er_diagram.html'))
    
    # Copy or place mermaid.min.js next to the HTML file for clean local loading
    mermaid_src = os.path.join('scripts', 'mermaid.min.js')
    mermaid_dst = os.path.join('docs', 'mermaid.min.js')
    if os.path.exists(mermaid_src):
        with open(mermaid_src, 'r', encoding='utf-8') as src, open(mermaid_dst, 'w', encoding='utf-8') as dst:
            dst.write(src.read())
            
    html_content = HTML_TEMPLATE.format(mermaid_code=MERMAID_CODE)
    with open(html_path, 'w', encoding='utf-8') as f:
        f.write(html_content)
    print(f"HTML generated at: {html_path}")
    
    # Save mermaid raw code
    mmd_path = os.path.join('docs', 'skillops_overall_er_diagram.mmd')
    with open(mmd_path, 'w', encoding='utf-8') as f:
        f.write(MERMAID_CODE)
    print(f"Mermaid file saved at: {mmd_path}")

    # Launch Chrome Headless with DevTools Protocol
    chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"
    port = 9223
    proc = subprocess.Popen([
        chrome_path,
        '--headless=new',
        f'--remote-debugging-port={port}',
        '--remote-allow-origins=*',
        '--disable-gpu',
        '--allow-file-access-from-files',
        '--no-sandbox',
        '--force-device-scale-factor=1'
    ])
    
    try:
        time.sleep(2)
        # Query CDP targets
        res = requests.get(f'http://127.0.0.1:{port}/json', timeout=10).json()
        target = res[0]
        ws_url = target['webSocketDebuggerUrl']
        ws = websocket.create_connection(ws_url, timeout=30)
        
        msg_id = 1
        def send_cmd(method, params=None):
            nonlocal msg_id
            m = {'id': msg_id, 'method': method, 'params': params or {}}
            msg_id += 1
            ws.send(json.dumps(m))
            while True:
                resp = json.loads(ws.recv())
                if resp.get('id') == m['id']:
                    return resp.get('result', {})

        send_cmd('Page.enable')
        send_cmd('DOM.enable')
        
        # Navigate to the HTML file
        file_url = 'file:///' + html_path.replace('\\', '/')
        print(f"Navigating Chrome to: {file_url}")
        send_cmd('Page.navigate', {'url': file_url})
        
        # Poll for renderComplete
        print("Waiting for diagram render...")
        for _ in range(60):
            time.sleep(0.5)
            eval_res = send_cmd('Runtime.evaluate', {'expression': 'window.renderComplete'})
            if eval_res.get('result', {}).get('value') is True:
                err_res = send_cmd('Runtime.evaluate', {'expression': 'window.renderError'})
                err_val = err_res.get('result', {}).get('value')
                if err_val:
                    raise RuntimeError(f"Mermaid Render Error: {err_val}")
                break
        else:
            raise TimeoutError("Diagram rendering timed out")
            
        print("Render successful! Measuring SVG bounding box...")
        bbox_res = send_cmd('Runtime.evaluate', {
            'expression': '''(() => {
                const svg = document.querySelector('svg');
                if (!svg) return null;
                const rect = svg.getBoundingClientRect();
                return {
                    x: Math.floor(rect.x),
                    y: Math.floor(rect.y),
                    width: Math.ceil(rect.width),
                    height: Math.ceil(rect.height)
                };
            })()''',
            'returnByValue': True
        })
        
        bbox = bbox_res.get('result', {}).get('value')
        print(f"SVG Dimensions: {bbox}")
        
        # Extract SVG code directly to save infinite-clarity SVG
        svg_res = send_cmd('Runtime.evaluate', {
            'expression': "document.querySelector('svg').outerHTML",
            'returnByValue': True
        })
        svg_content = svg_res.get('result', {}).get('value')
        svg_path = os.path.join('docs', 'skillops_overall_er_diagram.svg')
        with open(svg_path, 'w', encoding='utf-8') as f:
            f.write(svg_content)
        print(f"SVG saved at: {svg_path} ({len(svg_content)} bytes)")
        
        # Take high-resolution screenshot via CDP Page.captureScreenshot
        # For ultra clarity, we use scale=2.0 (high DPI / retina resolution)
        print("Capturing Ultra-Clarity Screenshot (scale=2.0)...")
        # Set viewport to accommodate diagram
        clip_box = {
            'x': max(0, bbox['x'] - 30),
            'y': max(0, bbox['y'] - 30),
            'width': bbox['width'] + 60,
            'height': bbox['height'] + 60,
            'scale': 2.0
        }
        
        shot_res = send_cmd('Page.captureScreenshot', {
            'format': 'png',
            'clip': clip_box,
            'captureBeyondViewport': True
        })
        
        png_data = base64.b64decode(shot_res['data'])
        png_path = os.path.join('docs', 'skillops_overall_er_diagram.png')
        with open(png_path, 'wb') as f:
            f.write(png_data)
        print(f"Ultra-Clarity PNG saved at: {png_path} ({len(png_data)} bytes)")
        
        # Convert PNG to high-quality JPG using Pillow
        jpg_path = os.path.join('docs', 'skillops_overall_er_diagram.jpg')
        with Image.open(png_path) as img:
            rgb_img = img.convert('RGB')
            # Save with maximum clarity and quality (subsampling=0, quality=98)
            rgb_img.save(jpg_path, 'JPEG', quality=98, subsampling=0)
            print(f"Ultra-Clarity JPG saved at: {jpg_path} (Size: {rgb_img.size})")

        ws.close()
    finally:
        proc.terminate()
        try:
            proc.wait(timeout=3)
        except Exception:
            proc.kill()

if __name__ == '__main__':
    generate()
