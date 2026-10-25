-- SkillOps domains 6-10: schemas (pgAdmin folders), tables, and seed data

CREATE SCHEMA IF NOT EXISTS assessments;
CREATE SCHEMA IF NOT EXISTS recruitment;
CREATE SCHEMA IF NOT EXISTS interviews;
CREATE SCHEMA IF NOT EXISTS notifications;
CREATE SCHEMA IF NOT EXISTS analytics;

COMMENT ON SCHEMA assessments IS 'Domain 6: Assessments';
COMMENT ON SCHEMA recruitment IS 'Domain 8: Recruitment';
COMMENT ON SCHEMA interviews IS 'Domain 7: Interviews';
COMMENT ON SCHEMA notifications IS 'Domain 9: Notifications';
COMMENT ON SCHEMA analytics IS 'Domain 10: Analytics';

-- Domain 6: Assessments
CREATE TABLE IF NOT EXISTS assessments.assessments (
    id                BIGSERIAL PRIMARY KEY,
    title             VARCHAR(255) NOT NULL,
    assessment_type   VARCHAR(80) NOT NULL,
    difficulty        VARCHAR(40),
    total_marks       INTEGER NOT NULL,
    duration_minutes  INTEGER NOT NULL,
    passing_marks     INTEGER NOT NULL,
    is_published      BOOLEAN NOT NULL DEFAULT FALSE,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS assessments.assessment_questions (
    id              BIGSERIAL PRIMARY KEY,
    assessment_id   BIGINT NOT NULL REFERENCES assessments.assessments (id) ON DELETE CASCADE,
    question_type   VARCHAR(40) NOT NULL,
    question        VARCHAR(2000) NOT NULL,
    marks           INTEGER NOT NULL,
    sequence        INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS assessments.coding_challenges (
    id              BIGSERIAL PRIMARY KEY,
    assessment_id   BIGINT NOT NULL REFERENCES assessments.assessments (id) ON DELETE CASCADE,
    title           VARCHAR(255) NOT NULL,
    language        VARCHAR(40),
    difficulty      VARCHAR(40),
    max_score       INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS assessments.assessment_attempts (
    id              BIGSERIAL PRIMARY KEY,
    assessment_id   BIGINT NOT NULL REFERENCES assessments.assessments (id) ON DELETE CASCADE,
    profile_id      BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    started_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    submitted_at    TIMESTAMPTZ,
    obtained_marks  INTEGER,
    passed          BOOLEAN,
    status          VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS assessments.coding_submissions (
    id                 BIGSERIAL PRIMARY KEY,
    challenge_id       BIGINT NOT NULL REFERENCES assessments.coding_challenges (id) ON DELETE CASCADE,
    profile_id         BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    repository_url     VARCHAR(500),
    source_code_url    VARCHAR(500),
    execution_score    INTEGER,
    quality_score      INTEGER,
    submitted_at       TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS assessments.evaluation_results (
    id                    BIGSERIAL PRIMARY KEY,
    attempt_id            BIGINT REFERENCES assessments.assessment_attempts (id) ON DELETE SET NULL,
    coding_submission_id  BIGINT REFERENCES assessments.coding_submissions (id) ON DELETE SET NULL,
    objective_score       INTEGER,
    coding_score          INTEGER,
    ai_score              INTEGER,
    final_score           INTEGER,
    remarks               VARCHAR(2000)
);

CREATE TABLE IF NOT EXISTS assessments.certificates (
    id                  BIGSERIAL PRIMARY KEY,
    assessment_id       BIGINT NOT NULL REFERENCES assessments.assessments (id) ON DELETE CASCADE,
    profile_id          BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    certificate_number  VARCHAR(80) NOT NULL UNIQUE,
    certificate_url     VARCHAR(500),
    issued_on           DATE,
    verification_hash   VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS assessments.assessment_feedback (
    id                    BIGSERIAL PRIMARY KEY,
    evaluation_result_id  BIGINT NOT NULL REFERENCES assessments.evaluation_results (id) ON DELETE CASCADE,
    strengths             VARCHAR(2000),
    weaknesses            VARCHAR(2000),
    recommendations       VARCHAR(2000),
    generated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Domain 8: Recruitment (before interviews because interviews.application_id)
CREATE TABLE IF NOT EXISTS recruitment.companies (
    id              BIGSERIAL PRIMARY KEY,
    company_name    VARCHAR(255) NOT NULL,
    industry        VARCHAR(120),
    website         VARCHAR(255),
    headquarters    VARCHAR(120),
    employee_count  INTEGER,
    company_type    VARCHAR(80),
    verified        BOOLEAN NOT NULL DEFAULT FALSE,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS recruitment.jobs (
    id                    BIGSERIAL PRIMARY KEY,
    company_id            BIGINT NOT NULL REFERENCES recruitment.companies (id) ON DELETE CASCADE,
    job_title             VARCHAR(255) NOT NULL,
    department            VARCHAR(120),
    employment_type       VARCHAR(40),
    work_mode             VARCHAR(40),
    location              VARCHAR(120),
    salary_min            NUMERIC(12, 2),
    salary_max            NUMERIC(12, 2),
    experience_level      VARCHAR(40),
    application_deadline  DATE,
    status                VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS recruitment.job_requirements (
    id                 BIGSERIAL PRIMARY KEY,
    job_id             BIGINT NOT NULL REFERENCES recruitment.jobs (id) ON DELETE CASCADE,
    requirement_type   VARCHAR(80) NOT NULL,
    description        VARCHAR(1000) NOT NULL,
    mandatory          BOOLEAN NOT NULL DEFAULT TRUE,
    priority           INTEGER
);

CREATE TABLE IF NOT EXISTS recruitment.applications (
    id                  BIGSERIAL PRIMARY KEY,
    job_id              BIGINT NOT NULL REFERENCES recruitment.jobs (id) ON DELETE CASCADE,
    profile_id          BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    resume_id           BIGINT REFERENCES user_profiles.resumes (id) ON DELETE SET NULL,
    applied_on          DATE NOT NULL DEFAULT CURRENT_DATE,
    application_status  VARCHAR(40) NOT NULL,
    ai_match_score      INTEGER,
    shortlisted         BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS recruitment.candidate_matches (
    id               BIGSERIAL PRIMARY KEY,
    application_id   BIGINT NOT NULL UNIQUE REFERENCES recruitment.applications (id) ON DELETE CASCADE,
    skill_score      INTEGER,
    project_score    INTEGER,
    assessment_score INTEGER,
    interview_score  INTEGER,
    overall_score    INTEGER,
    recommendation   VARCHAR(255),
    generated_at     TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS recruitment.offers (
    id              BIGSERIAL PRIMARY KEY,
    application_id  BIGINT NOT NULL REFERENCES recruitment.applications (id) ON DELETE CASCADE,
    designation     VARCHAR(120) NOT NULL,
    offered_ctc     NUMERIC(12, 2),
    joining_date    DATE,
    offer_expiry    DATE,
    offer_status    VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS recruitment.hiring_status (
    id                       BIGSERIAL PRIMARY KEY,
    application_id           BIGINT NOT NULL UNIQUE REFERENCES recruitment.applications (id) ON DELETE CASCADE,
    current_stage            VARCHAR(80) NOT NULL,
    background_verification  BOOLEAN NOT NULL DEFAULT FALSE,
    documents_verified       BOOLEAN NOT NULL DEFAULT FALSE,
    onboarding_completed     BOOLEAN NOT NULL DEFAULT FALSE,
    updated_at               TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Domain 7: Interviews
CREATE TABLE IF NOT EXISTS interviews.interviews (
    id                     BIGSERIAL PRIMARY KEY,
    application_id         BIGINT REFERENCES recruitment.applications (id) ON DELETE SET NULL,
    candidate_profile_id   BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    interview_type         VARCHAR(80),
    mode                   VARCHAR(40),
    scheduled_date         DATE,
    scheduled_time         TIME,
    duration_minutes       INTEGER,
    status                 VARCHAR(40) NOT NULL,
    meeting_link           VARCHAR(500),
    created_at             TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS interviews.interview_rounds (
    id                 BIGSERIAL PRIMARY KEY,
    interview_id       BIGINT NOT NULL REFERENCES interviews.interviews (id) ON DELETE CASCADE,
    round_name         VARCHAR(120) NOT NULL,
    round_type         VARCHAR(80),
    round_number       INTEGER NOT NULL,
    interviewer        VARCHAR(120),
    duration_minutes   INTEGER,
    status             VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS interviews.interview_feedback (
    id                       BIGSERIAL PRIMARY KEY,
    round_id                 BIGINT NOT NULL REFERENCES interviews.interview_rounds (id) ON DELETE CASCADE,
    reviewer_id              BIGINT REFERENCES user_profiles.profiles (id) ON DELETE SET NULL,
    communication_rating     INTEGER,
    technical_rating         INTEGER,
    problem_solving_rating   INTEGER,
    confidence_rating        INTEGER,
    strengths                VARCHAR(2000),
    improvements             VARCHAR(2000),
    remarks                  VARCHAR(2000),
    submitted_at             TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS interviews.interview_scorecards (
    id                   BIGSERIAL PRIMARY KEY,
    interview_id         BIGINT NOT NULL UNIQUE REFERENCES interviews.interviews (id) ON DELETE CASCADE,
    communication_score  INTEGER,
    technical_score      INTEGER,
    aptitude_score       INTEGER,
    behavioral_score     INTEGER,
    coding_score         INTEGER,
    final_score          INTEGER,
    recommendation       VARCHAR(255),
    generated_at         TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS interviews.ai_interview_reports (
    id                      BIGSERIAL PRIMARY KEY,
    interview_id            BIGINT NOT NULL REFERENCES interviews.interviews (id) ON DELETE CASCADE,
    scorecard_id            BIGINT REFERENCES interviews.interview_scorecards (id) ON DELETE SET NULL,
    confidence_score        NUMERIC(5, 2),
    strengths               VARCHAR(2000),
    weaknesses              VARCHAR(2000),
    improvement_areas       VARCHAR(2000),
    suggested_learning_path VARCHAR(500),
    ai_summary              VARCHAR(2000),
    generated_at            TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Domain 9: Notifications
CREATE TABLE IF NOT EXISTS notifications.notification_preferences (
    id                     BIGSERIAL PRIMARY KEY,
    user_id                BIGINT NOT NULL UNIQUE REFERENCES identity_authentication.users (id) ON DELETE CASCADE,
    email_enabled          BOOLEAN NOT NULL DEFAULT TRUE,
    push_enabled           BOOLEAN NOT NULL DEFAULT TRUE,
    sms_enabled            BOOLEAN NOT NULL DEFAULT FALSE,
    whatsapp_enabled       BOOLEAN NOT NULL DEFAULT FALSE,
    in_app_enabled         BOOLEAN NOT NULL DEFAULT TRUE,
    marketing_enabled      BOOLEAN NOT NULL DEFAULT FALSE,
    reminder_enabled       BOOLEAN NOT NULL DEFAULT TRUE,
    system_alerts_enabled  BOOLEAN NOT NULL DEFAULT TRUE,
    preferred_language     VARCHAR(40),
    timezone               VARCHAR(80),
    updated_at             TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS notifications.notifications (
    id                  BIGSERIAL PRIMARY KEY,
    user_id             BIGINT NOT NULL REFERENCES identity_authentication.users (id) ON DELETE CASCADE,
    preference_id       BIGINT REFERENCES notifications.notification_preferences (id) ON DELETE SET NULL,
    title               VARCHAR(255) NOT NULL,
    message             VARCHAR(2000) NOT NULL,
    notification_type   VARCHAR(80),
    delivery_channel    VARCHAR(40),
    priority            VARCHAR(40),
    status              VARCHAR(40) NOT NULL,
    reference_module    VARCHAR(80),
    reference_id        BIGINT,
    is_read             BOOLEAN NOT NULL DEFAULT FALSE,
    scheduled_at        TIMESTAMPTZ,
    sent_at             TIMESTAMPTZ,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Domain 10: Analytics
CREATE TABLE IF NOT EXISTS analytics.dashboards (
    id              BIGSERIAL PRIMARY KEY,
    profile_id      BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    dashboard_type  VARCHAR(80) NOT NULL,
    dashboard_name  VARCHAR(120) NOT NULL,
    widgets_count   INTEGER NOT NULL DEFAULT 0,
    is_default      BOOLEAN NOT NULL DEFAULT FALSE,
    last_refreshed  TIMESTAMPTZ,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS analytics.user_analytics (
    id                      BIGSERIAL PRIMARY KEY,
    dashboard_id            BIGINT NOT NULL REFERENCES analytics.dashboards (id) ON DELETE CASCADE,
    profile_id              BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    learning_score          INTEGER,
    skill_score             INTEGER,
    assessment_score        INTEGER,
    project_score           INTEGER,
    interview_score         INTEGER,
    employability_score     INTEGER,
    profile_completion      INTEGER,
    total_courses           INTEGER,
    completed_courses       INTEGER,
    total_projects          INTEGER,
    completed_projects      INTEGER,
    certificates_earned     INTEGER,
    applications_submitted  INTEGER,
    interviews_completed    INTEGER,
    offers_received         INTEGER,
    streak_days             INTEGER,
    current_rank            VARCHAR(40),
    recommendation          VARCHAR(500),
    calculated_at           TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Seed Domain 6
INSERT INTO assessments.assessments (title, assessment_type, difficulty, total_marks, duration_minutes, passing_marks, is_published) VALUES
('Java Backend Certification', 'CERTIFICATION', 'INTERMEDIATE', 100, 90, 70, TRUE),
('SQL Practical Exam', 'PRACTICAL', 'BEGINNER', 50, 45, 30, TRUE),
('React Frontend Challenge', 'CODING', 'INTERMEDIATE', 100, 120, 60, TRUE);

INSERT INTO assessments.assessment_questions (assessment_id, question_type, question, marks, sequence)
SELECT a.id, v.qtype, v.question, v.marks, v.seq
FROM (VALUES
    ('Java Backend Certification', 'MCQ', 'Which annotation maps a Spring REST controller?', 5, 1),
    ('Java Backend Certification', 'MCQ', 'What does JPA GenerationType.IDENTITY use in PostgreSQL?', 5, 2),
    ('SQL Practical Exam', 'PRACTICAL', 'Write a join to list users and their roles.', 20, 1),
    ('React Frontend Challenge', 'MCQ', 'What hook manages local component state?', 10, 1)
) AS v(title, qtype, question, marks, seq)
JOIN assessments.assessments a ON a.title = v.title;

INSERT INTO assessments.coding_challenges (assessment_id, title, language, difficulty, max_score)
SELECT a.id, v.title, v.lang, v.diff, v.score
FROM (VALUES
    ('Java Backend Certification', 'Implement a paginated users API', 'Java', 'INTERMEDIATE', 40),
    ('React Frontend Challenge', 'Build a profile card component', 'JavaScript', 'BEGINNER', 50)
) AS v(assessment, title, lang, diff, score)
JOIN assessments.assessments a ON a.title = v.assessment;

INSERT INTO assessments.assessment_attempts (assessment_id, profile_id, started_at, submitted_at, obtained_marks, passed, status)
SELECT a.id, p.id, NOW() - INTERVAL '2 days', NOW() - INTERVAL '2 days' + INTERVAL '70 minutes', v.marks, v.passed, v.status
FROM (VALUES
    ('priya.student@apex-university.edu', 'Java Backend Certification', 82, TRUE, 'SUBMITTED'),
    ('rohan.student@apex-university.edu', 'SQL Practical Exam', 41, TRUE, 'SUBMITTED'),
    ('meera.student@apex-university.edu', 'React Frontend Challenge', 55, FALSE, 'SUBMITTED'),
    ('vikram.student@apex-university.edu', 'Java Backend Certification', NULL, NULL, 'IN_PROGRESS')
) AS v(email, assessment, marks, passed, status)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN assessments.assessments a ON a.title = v.assessment;

INSERT INTO assessments.coding_submissions (challenge_id, profile_id, repository_url, source_code_url, execution_score, quality_score)
SELECT c.id, p.id, v.repo, v.src, v.exec, v.quality
FROM (VALUES
    ('priya.student@apex-university.edu', 'Implement a paginated users API', 'https://github.com/priyadeshmukh/users-api', '/code/priya-users.java', 36, 32),
    ('meera.student@apex-university.edu', 'Build a profile card component', 'https://github.com/meerakulkarni/profile-card', '/code/meera-card.jsx', 28, 24)
) AS v(email, challenge, repo, src, exec, quality)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN assessments.coding_challenges c ON c.title = v.challenge;

INSERT INTO assessments.evaluation_results (attempt_id, coding_submission_id, objective_score, coding_score, ai_score, final_score, remarks)
SELECT att.id, cs.id, 42, 36, 80, 82, 'Strong API design; add pagination tests.'
FROM assessments.assessment_attempts att
JOIN assessments.assessments a ON a.id = att.assessment_id AND a.title = 'Java Backend Certification'
JOIN user_profiles.profiles p ON p.id = att.profile_id
JOIN identity_authentication.users u ON u.id = p.user_id AND u.email = 'priya.student@apex-university.edu'
JOIN assessments.coding_challenges c ON c.title = 'Implement a paginated users API'
JOIN assessments.coding_submissions cs ON cs.challenge_id = c.id AND cs.profile_id = p.id;

INSERT INTO assessments.evaluation_results (attempt_id, coding_submission_id, objective_score, coding_score, ai_score, final_score, remarks)
SELECT att.id, NULL, 41, NULL, 70, 41, 'SQL joins are correct; add window functions next.'
FROM assessments.assessment_attempts att
JOIN assessments.assessments a ON a.id = att.assessment_id AND a.title = 'SQL Practical Exam'
JOIN user_profiles.profiles p ON p.id = att.profile_id
JOIN identity_authentication.users u ON u.id = p.user_id AND u.email = 'rohan.student@apex-university.edu';

INSERT INTO assessments.certificates (assessment_id, profile_id, certificate_number, certificate_url, issued_on, verification_hash)
SELECT a.id, p.id, v.num, v.url, CURRENT_DATE - 10, v.hash
FROM (VALUES
    ('priya.student@apex-university.edu', 'Java Backend Certification', 'SKL-JAVA-00012', 'https://certs.skillops.io/SKL-JAVA-00012', 'hash-priya-java'),
    ('rohan.student@apex-university.edu', 'SQL Practical Exam', 'SKL-SQL-00008', 'https://certs.skillops.io/SKL-SQL-00008', 'hash-rohan-sql')
) AS v(email, assessment, num, url, hash)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN assessments.assessments a ON a.title = v.assessment;

INSERT INTO assessments.assessment_feedback (evaluation_result_id, strengths, weaknesses, recommendations)
SELECT er.id, 'Clear REST layering and naming.', 'Limited test coverage.', 'Add MockMvc tests for pagination.'
FROM assessments.evaluation_results er
WHERE er.final_score = 82;

INSERT INTO assessments.assessment_feedback (evaluation_result_id, strengths, weaknesses, recommendations)
SELECT er.id, 'Accurate join logic.', 'No explanation of indexes.', 'Practice EXPLAIN ANALYZE on large joins.'
FROM assessments.evaluation_results er
WHERE er.final_score = 41;

-- Seed Domain 8
INSERT INTO recruitment.companies (company_name, industry, website, headquarters, employee_count, company_type, verified) VALUES
('NovaTech', 'Product Software', 'https://novatech.com', 'Hyderabad', 1200, 'PRIVATE', TRUE),
('InsightLabs', 'Analytics', 'https://insightlabs.io', 'Pune', 350, 'STARTUP', TRUE),
('CloudNest', 'Cloud Infrastructure', 'https://cloudnest.io', 'Bengaluru', 800, 'PRIVATE', TRUE);

INSERT INTO recruitment.jobs (company_id, job_title, department, employment_type, work_mode, location, salary_min, salary_max, experience_level, application_deadline, status)
SELECT c.id, v.title, v.dept, v.emp, v.mode, v.loc, v.smin, v.smax, v.exp, CURRENT_DATE + 30, 'OPEN'
FROM (VALUES
    ('NovaTech', 'Backend Engineer', 'Engineering', 'FULL_TIME', 'HYBRID', 'Mumbai', 1000000, 1600000, 'FRESHER', TRUE),
    ('InsightLabs', 'Data Engineer', 'Data', 'FULL_TIME', 'ONSITE', 'Pune', 900000, 1500000, 'FRESHER', TRUE),
    ('CloudNest', 'Cloud Engineer', 'Platform', 'FULL_TIME', 'REMOTE', 'Hyderabad', 1100000, 1800000, 'JUNIOR', TRUE)
) AS v(company, title, dept, emp, mode, loc, smin, smax, exp, unused)
JOIN recruitment.companies c ON c.company_name = v.company;

INSERT INTO recruitment.job_requirements (job_id, requirement_type, description, mandatory, priority)
SELECT j.id, v.rtype, v.descr, v.mand, v.pri
FROM (VALUES
    ('Backend Engineer', 'SKILL', 'Java and Spring Boot', TRUE, 1),
    ('Backend Engineer', 'SKILL', 'PostgreSQL', TRUE, 2),
    ('Data Engineer', 'SKILL', 'SQL and Python', TRUE, 1),
    ('Cloud Engineer', 'SKILL', 'Docker and Kubernetes', TRUE, 1),
    ('Cloud Engineer', 'EDUCATION', 'B.E. or equivalent', FALSE, 2)
) AS v(job, rtype, descr, mand, pri)
JOIN recruitment.jobs j ON j.job_title = v.job;

INSERT INTO recruitment.applications (job_id, profile_id, resume_id, applied_on, application_status, ai_match_score, shortlisted)
SELECT j.id, p.id, r.id, CURRENT_DATE - v.days, v.status, v.score, v.shortlisted
FROM (VALUES
    ('priya.student@apex-university.edu', 'Backend Engineer', 'SHORTLISTED', 88, TRUE, 5),
    ('rohan.student@apex-university.edu', 'Data Engineer', 'UNDER_REVIEW', 81, TRUE, 3),
    ('vikram.student@apex-university.edu', 'Cloud Engineer', 'APPLIED', 79, FALSE, 2),
    ('meera.student@apex-university.edu', 'Backend Engineer', 'REJECTED', 54, FALSE, 8)
) AS v(email, job, status, score, shortlisted, days)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN recruitment.jobs j ON j.job_title = v.job
LEFT JOIN LATERAL (
    SELECT resumes.id FROM user_profiles.resumes WHERE resumes.profile_id = p.id AND resumes.is_primary = TRUE LIMIT 1
) r ON TRUE;

INSERT INTO recruitment.candidate_matches (application_id, skill_score, project_score, assessment_score, interview_score, overall_score, recommendation)
SELECT app.id, v.skill, v.project, v.assess, v.interview, v.overall, v.rec
FROM (VALUES
    ('priya.student@apex-university.edu', 'Backend Engineer', 85, 80, 82, 0, 84, 'STRONG_MATCH'),
    ('rohan.student@apex-university.edu', 'Data Engineer', 80, 75, 78, 0, 78, 'MATCH'),
    ('vikram.student@apex-university.edu', 'Cloud Engineer', 88, 82, 70, 0, 80, 'MATCH')
) AS v(email, job, skill, project, assess, interview, overall, rec)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN recruitment.jobs j ON j.job_title = v.job
JOIN recruitment.applications app ON app.profile_id = p.id AND app.job_id = j.id;

INSERT INTO recruitment.offers (application_id, designation, offered_ctc, joining_date, offer_expiry, offer_status)
SELECT app.id, 'Backend Engineer', 1250000, CURRENT_DATE + 45, CURRENT_DATE + 14, 'PENDING'
FROM recruitment.applications app
JOIN user_profiles.profiles p ON p.id = app.profile_id
JOIN identity_authentication.users u ON u.id = p.user_id AND u.email = 'priya.student@apex-university.edu'
JOIN recruitment.jobs j ON j.id = app.job_id AND j.job_title = 'Backend Engineer';

INSERT INTO recruitment.hiring_status (application_id, current_stage, background_verification, documents_verified, onboarding_completed)
SELECT app.id, v.stage, v.bg, v.docs, v.onb
FROM (VALUES
    ('priya.student@apex-university.edu', 'Backend Engineer', 'INTERVIEW', FALSE, TRUE, FALSE),
    ('rohan.student@apex-university.edu', 'Data Engineer', 'SCREENING', FALSE, FALSE, FALSE),
    ('vikram.student@apex-university.edu', 'Cloud Engineer', 'APPLIED', FALSE, FALSE, FALSE)
) AS v(email, job, stage, bg, docs, onb)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN recruitment.jobs j ON j.job_title = v.job
JOIN recruitment.applications app ON app.profile_id = p.id AND app.job_id = j.id;

-- Seed Domain 7
INSERT INTO interviews.interviews (application_id, candidate_profile_id, interview_type, mode, scheduled_date, scheduled_time, duration_minutes, status, meeting_link)
SELECT app.id, p.id, v.itype, v.mode, CURRENT_DATE + v.day_off, TIME '11:00', v.mins, v.status, v.link
FROM (VALUES
    ('priya.student@apex-university.edu', 'Backend Engineer', 'TECHNICAL', 'ONLINE', 2, 60, 'SCHEDULED', 'https://meet.skillops.io/priya-novatech'),
    ('rohan.student@apex-university.edu', 'Data Engineer', 'HR', 'ONSITE', 5, 30, 'SCHEDULED', NULL),
    ('vikram.student@apex-university.edu', 'Cloud Engineer', 'AI_MOCK', 'ONLINE', -1, 45, 'COMPLETED', 'https://meet.skillops.io/vikram-ai')
) AS v(email, job, itype, mode, day_off, mins, status, link)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN recruitment.jobs j ON j.job_title = v.job
JOIN recruitment.applications app ON app.profile_id = p.id AND app.job_id = j.id;

INSERT INTO interviews.interview_rounds (interview_id, round_name, round_type, round_number, interviewer, duration_minutes, status)
SELECT i.id, v.rname, v.rtype, v.rno, v.interviewer, v.mins, v.status
FROM (VALUES
    ('priya.student@apex-university.edu', 'Coding Round', 'CODING', 1, 'Anita Sharma', 45, 'COMPLETED'),
    ('priya.student@apex-university.edu', 'System Design', 'TECHNICAL', 2, 'Kavya Nair', 45, 'SCHEDULED'),
    ('vikram.student@apex-university.edu', 'AI Mock Round', 'AI', 1, 'SkillOps AI', 40, 'COMPLETED')
) AS v(email, rname, rtype, rno, interviewer, mins, status)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN interviews.interviews i ON i.candidate_profile_id = p.id AND i.interview_type IN ('TECHNICAL', 'AI_MOCK')
  AND (
    (v.email LIKE 'priya%' AND i.interview_type = 'TECHNICAL')
    OR (v.email LIKE 'vikram%' AND i.interview_type = 'AI_MOCK')
  );

INSERT INTO interviews.interview_feedback (round_id, reviewer_id, communication_rating, technical_rating, problem_solving_rating, confidence_rating, strengths, improvements, remarks)
SELECT r.id, rp.id, 8, 9, 8, 7, 'Clear problem breakdown.', 'Discuss time complexity earlier.', 'Recommend next round.'
FROM interviews.interview_rounds r
JOIN interviews.interviews i ON i.id = r.interview_id
JOIN user_profiles.profiles cp ON cp.id = i.candidate_profile_id
JOIN identity_authentication.users cu ON cu.id = cp.user_id AND cu.email = 'priya.student@apex-university.edu'
JOIN identity_authentication.users ru ON ru.email = 'recruiter.anita@novatech.com'
JOIN user_profiles.profiles rp ON rp.user_id = ru.id
WHERE r.round_number = 1 AND r.round_name = 'Coding Round';

INSERT INTO interviews.interview_scorecards (interview_id, communication_score, technical_score, aptitude_score, behavioral_score, coding_score, final_score, recommendation)
SELECT i.id, 78, 85, 80, 74, 88, 81, 'PROCEED'
FROM interviews.interviews i
JOIN user_profiles.profiles p ON p.id = i.candidate_profile_id
JOIN identity_authentication.users u ON u.id = p.user_id AND u.email = 'priya.student@apex-university.edu'
WHERE i.interview_type = 'TECHNICAL';

INSERT INTO interviews.interview_scorecards (interview_id, communication_score, technical_score, aptitude_score, behavioral_score, coding_score, final_score, recommendation)
SELECT i.id, 70, 82, 75, 72, 84, 77, 'HOLD'
FROM interviews.interviews i
JOIN user_profiles.profiles p ON p.id = i.candidate_profile_id
JOIN identity_authentication.users u ON u.id = p.user_id AND u.email = 'vikram.student@apex-university.edu'
WHERE i.interview_type = 'AI_MOCK';

INSERT INTO interviews.ai_interview_reports (interview_id, scorecard_id, confidence_score, strengths, weaknesses, improvement_areas, suggested_learning_path, ai_summary)
SELECT i.id, sc.id, 81.50, 'Solid Kubernetes fundamentals.', 'Needs deeper networking examples.', 'Service mesh and observability.', 'Cloud Native Java Path', 'Candidate is hire-ready for junior cloud roles after one more mock.'
FROM interviews.interviews i
JOIN interviews.interview_scorecards sc ON sc.interview_id = i.id
JOIN user_profiles.profiles p ON p.id = i.candidate_profile_id
JOIN identity_authentication.users u ON u.id = p.user_id AND u.email = 'vikram.student@apex-university.edu';

-- Seed Domain 9
INSERT INTO notifications.notification_preferences (user_id, email_enabled, push_enabled, sms_enabled, whatsapp_enabled, in_app_enabled, marketing_enabled, reminder_enabled, system_alerts_enabled, preferred_language, timezone)
SELECT u.id, TRUE, TRUE, v.sms, FALSE, TRUE, FALSE, TRUE, TRUE, 'en', 'Asia/Kolkata'
FROM (VALUES
    ('priya.student@apex-university.edu', FALSE),
    ('rohan.student@apex-university.edu', TRUE),
    ('meera.student@apex-university.edu', FALSE),
    ('vikram.student@apex-university.edu', FALSE),
    ('recruiter.anita@novatech.com', TRUE)
) AS v(email, sms)
JOIN identity_authentication.users u ON u.email = v.email;

INSERT INTO notifications.notifications (user_id, preference_id, title, message, notification_type, delivery_channel, priority, status, reference_module, reference_id, is_read, sent_at)
SELECT u.id, pref.id, v.title, v.message, v.ntype, v.channel, v.priority, 'SENT', v.module, 1, v.read, NOW() - INTERVAL '3 hours'
FROM (VALUES
    ('priya.student@apex-university.edu', 'Interview scheduled', 'Your NovaTech technical interview is scheduled.', 'INTERVIEW', 'EMAIL', 'HIGH', 'INTERVIEWS', FALSE),
    ('priya.student@apex-university.edu', 'Certificate issued', 'Java Backend Certification is now in your passport.', 'ASSESSMENT', 'IN_APP', 'MEDIUM', 'ASSESSMENTS', TRUE),
    ('rohan.student@apex-university.edu', 'Application received', 'InsightLabs received your Data Engineer application.', 'RECRUITMENT', 'PUSH', 'MEDIUM', 'RECRUITMENT', FALSE),
    ('vikram.student@apex-university.edu', 'AI mock complete', 'Your AI mock interview report is ready.', 'INTERVIEW', 'IN_APP', 'HIGH', 'INTERVIEWS', FALSE)
) AS v(email, title, message, ntype, channel, priority, module, read)
JOIN identity_authentication.users u ON u.email = v.email
JOIN notifications.notification_preferences pref ON pref.user_id = u.id;

-- Seed Domain 10
INSERT INTO analytics.dashboards (profile_id, dashboard_type, dashboard_name, widgets_count, is_default, last_refreshed)
SELECT p.id, v.dtype, v.dname, v.widgets, TRUE, NOW() - INTERVAL '1 hour'
FROM (VALUES
    ('priya.student@apex-university.edu', 'LEARNER', 'Priya Career Dashboard', 8),
    ('rohan.student@apex-university.edu', 'LEARNER', 'Rohan Data Dashboard', 6),
    ('vikram.student@apex-university.edu', 'LEARNER', 'Vikram Cloud Dashboard', 7),
    ('recruiter.anita@novatech.com', 'RECRUITER', 'NovaTech Hiring Dashboard', 5)
) AS v(email, dtype, dname, widgets)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO analytics.user_analytics (
    dashboard_id, profile_id, learning_score, skill_score, assessment_score, project_score, interview_score,
    employability_score, profile_completion, total_courses, completed_courses, total_projects, completed_projects,
    certificates_earned, applications_submitted, interviews_completed, offers_received, streak_days, current_rank, recommendation
)
SELECT d.id, p.id, v.learn, v.skill, v.assess, v.proj, v.interview, v.emp, v.completion, v.tc, v.cc, v.tp, v.cp, v.certs, v.apps, v.iv, v.offers, v.streak, v.rank, v.rec
FROM (VALUES
    ('priya.student@apex-university.edu', 78, 80, 82, 76, 81, 84, 92, 2, 1, 2, 1, 1, 1, 1, 1, 12, 'GOLD', 'Continue Kubernetes to close the remaining skill gap.'),
    ('rohan.student@apex-university.edu', 70, 74, 78, 72, 0, 76, 85, 1, 0, 1, 1, 1, 1, 0, 0, 8, 'SILVER', 'Complete Spark modules before the data interview.'),
    ('vikram.student@apex-university.edu', 75, 86, 70, 80, 77, 82, 88, 3, 1, 2, 1, 1, 1, 1, 0, 15, 'GOLD', 'Publish the containerized SkillOps project to the portfolio.')
) AS v(email, learn, skill, assess, proj, interview, emp, completion, tc, cc, tp, cp, certs, apps, iv, offers, streak, rank, rec)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN analytics.dashboards d ON d.profile_id = p.id AND d.is_default = TRUE;
