-- Organize first 5 SkillOps domains as PostgreSQL schemas (shown as folders in pgAdmin)

CREATE SCHEMA IF NOT EXISTS identity_authentication;
CREATE SCHEMA IF NOT EXISTS user_profiles;
CREATE SCHEMA IF NOT EXISTS skills;
CREATE SCHEMA IF NOT EXISTS learning;
CREATE SCHEMA IF NOT EXISTS projects;

COMMENT ON SCHEMA identity_authentication IS 'Domain 1: Identity & Authentication';
COMMENT ON SCHEMA user_profiles IS 'Domain 2: User Profiles';
COMMENT ON SCHEMA skills IS 'Domain 3: Skills';
COMMENT ON SCHEMA learning IS 'Domain 4: Learning';
COMMENT ON SCHEMA projects IS 'Domain 5: Projects';

DO $$
DECLARE
    t text;
BEGIN
    FOREACH t IN ARRAY ARRAY[
        'users', 'roles', 'permissions', 'user_roles',
        'sessions', 'refresh_tokens', 'login_history', 'audit_logs'
    ]
    LOOP
        IF EXISTS (
            SELECT 1 FROM information_schema.tables
            WHERE table_schema = 'public' AND table_name = t
        ) THEN
            EXECUTE format('ALTER TABLE public.%I SET SCHEMA identity_authentication', t);
        END IF;
    END LOOP;
END $$;

-- Domain 2: User Profiles
CREATE TABLE IF NOT EXISTS user_profiles.profiles (
    id               BIGSERIAL PRIMARY KEY,
    user_id          BIGINT NOT NULL UNIQUE REFERENCES identity_authentication.users (id) ON DELETE CASCADE,
    first_name       VARCHAR(80) NOT NULL,
    last_name        VARCHAR(80) NOT NULL,
    date_of_birth    DATE,
    gender           VARCHAR(20),
    bio              VARCHAR(2000),
    profile_picture  VARCHAR(500),
    city             VARCHAR(80),
    state            VARCHAR(80),
    country          VARCHAR(80),
    linkedin_url     VARCHAR(500),
    github_url       VARCHAR(500),
    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS user_profiles.resumes (
    id           BIGSERIAL PRIMARY KEY,
    profile_id   BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    file_name    VARCHAR(255) NOT NULL,
    file_url     VARCHAR(500) NOT NULL,
    file_type    VARCHAR(40),
    parsed_text  TEXT,
    version      INTEGER NOT NULL DEFAULT 1,
    is_primary   BOOLEAN NOT NULL DEFAULT FALSE,
    uploaded_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS user_profiles.educations (
    id               BIGSERIAL PRIMARY KEY,
    profile_id       BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    institute_name   VARCHAR(255) NOT NULL,
    degree           VARCHAR(120) NOT NULL,
    specialization   VARCHAR(120),
    cgpa             NUMERIC(4, 2),
    passing_year     INTEGER,
    start_year       INTEGER,
    end_year         INTEGER,
    is_current       BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS user_profiles.experiences (
    id                BIGSERIAL PRIMARY KEY,
    profile_id        BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    company_name      VARCHAR(255) NOT NULL,
    designation       VARCHAR(120) NOT NULL,
    employment_type   VARCHAR(40),
    start_date        DATE NOT NULL,
    end_date          DATE,
    currently_working BOOLEAN NOT NULL DEFAULT FALSE,
    location          VARCHAR(120),
    responsibilities  TEXT
);

CREATE TABLE IF NOT EXISTS user_profiles.certifications (
    id                  BIGSERIAL PRIMARY KEY,
    profile_id          BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    certification_name  VARCHAR(255) NOT NULL,
    issuer              VARCHAR(255) NOT NULL,
    credential_id       VARCHAR(120),
    credential_url      VARCHAR(500),
    issue_date          DATE,
    expiry_date         DATE
);

CREATE TABLE IF NOT EXISTS user_profiles.career_goals (
    id                      BIGSERIAL PRIMARY KEY,
    profile_id              BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    target_role             VARCHAR(120) NOT NULL,
    target_industry         VARCHAR(120),
    target_company          VARCHAR(120),
    preferred_location      VARCHAR(120),
    expected_ctc            NUMERIC(12, 2),
    employment_type         VARCHAR(40),
    target_timeline_months  INTEGER,
    created_at              TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS user_profiles.skill_passports (
    id                   BIGSERIAL PRIMARY KEY,
    profile_id           BIGINT NOT NULL UNIQUE REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    passport_score       NUMERIC(5, 2) NOT NULL DEFAULT 0,
    total_skills         INTEGER NOT NULL DEFAULT 0,
    verified_skills      INTEGER NOT NULL DEFAULT 0,
    completed_courses    INTEGER NOT NULL DEFAULT 0,
    completed_projects   INTEGER NOT NULL DEFAULT 0,
    certificates_earned  INTEGER NOT NULL DEFAULT 0,
    verification_status  VARCHAR(40) NOT NULL DEFAULT 'PENDING',
    last_updated         TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Domain 3: Skills
CREATE TABLE IF NOT EXISTS skills.skill_categories (
    id            BIGSERIAL PRIMARY KEY,
    category_name VARCHAR(120) NOT NULL UNIQUE,
    description   VARCHAR(500),
    icon          VARCHAR(120),
    is_active     BOOLEAN NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS skills.skills (
    id                BIGSERIAL PRIMARY KEY,
    category_id       BIGINT NOT NULL REFERENCES skills.skill_categories (id) ON DELETE RESTRICT,
    skill_name        VARCHAR(120) NOT NULL,
    description       VARCHAR(500),
    difficulty_level  VARCHAR(40),
    is_verified       BOOLEAN NOT NULL DEFAULT FALSE,
    created_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_skills_category_name UNIQUE (category_id, skill_name)
);

CREATE TABLE IF NOT EXISTS skills.skill_assessments (
    id                BIGSERIAL PRIMARY KEY,
    skill_id          BIGINT NOT NULL REFERENCES skills.skills (id) ON DELETE CASCADE,
    assessment_type   VARCHAR(80) NOT NULL,
    passing_score     INTEGER NOT NULL,
    total_marks       INTEGER NOT NULL,
    duration_minutes  INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS skills.competencies (
    id               BIGSERIAL PRIMARY KEY,
    skill_id         BIGINT NOT NULL REFERENCES skills.skills (id) ON DELETE CASCADE,
    competency_name  VARCHAR(120) NOT NULL,
    description      VARCHAR(500),
    display_order    INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS skills.competency_levels (
    id             BIGSERIAL PRIMARY KEY,
    competency_id  BIGINT NOT NULL REFERENCES skills.competencies (id) ON DELETE CASCADE,
    level_name     VARCHAR(80) NOT NULL,
    level_value    INTEGER NOT NULL,
    criteria       VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS skills.user_skills (
    id                   BIGSERIAL PRIMARY KEY,
    profile_id           BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    skill_id             BIGINT NOT NULL REFERENCES skills.skills (id) ON DELETE RESTRICT,
    competency_level_id  BIGINT REFERENCES skills.competency_levels (id) ON DELETE SET NULL,
    skill_assessment_id  BIGINT REFERENCES skills.skill_assessments (id) ON DELETE SET NULL,
    proficiency_score    INTEGER NOT NULL DEFAULT 0,
    verified             BOOLEAN NOT NULL DEFAULT FALSE,
    last_used            DATE,
    updated_at           TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_user_skills UNIQUE (profile_id, skill_id)
);

CREATE TABLE IF NOT EXISTS skills.skill_gaps (
    id             BIGSERIAL PRIMARY KEY,
    profile_id     BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    skill_id       BIGINT NOT NULL REFERENCES skills.skills (id) ON DELETE RESTRICT,
    current_level  INTEGER NOT NULL,
    target_level   INTEGER NOT NULL,
    gap_score      INTEGER NOT NULL,
    priority       VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS skills.skill_recommendations (
    id                     BIGSERIAL PRIMARY KEY,
    profile_id             BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    skill_id               BIGINT NOT NULL REFERENCES skills.skills (id) ON DELETE RESTRICT,
    recommendation_reason  VARCHAR(500),
    recommendation_score   INTEGER,
    recommendation_type    VARCHAR(80),
    generated_at           TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Domain 4: Learning
CREATE TABLE IF NOT EXISTS learning.learning_paths (
    id                  BIGSERIAL PRIMARY KEY,
    profile_id          BIGINT REFERENCES user_profiles.profiles (id) ON DELETE SET NULL,
    title               VARCHAR(255) NOT NULL,
    description         VARCHAR(1000),
    difficulty          VARCHAR(40),
    estimated_duration  INTEGER,
    status              VARCHAR(40) NOT NULL DEFAULT 'ACTIVE',
    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS learning.courses (
    id                 BIGSERIAL PRIMARY KEY,
    learning_path_id   BIGINT REFERENCES learning.learning_paths (id) ON DELETE SET NULL,
    title              VARCHAR(255) NOT NULL,
    description        VARCHAR(1000),
    instructor         VARCHAR(120),
    duration_hours     INTEGER,
    level              VARCHAR(40),
    published          BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS learning.modules (
    id           BIGSERIAL PRIMARY KEY,
    course_id    BIGINT NOT NULL REFERENCES learning.courses (id) ON DELETE CASCADE,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(1000),
    sequence     INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS learning.lessons (
    id                BIGSERIAL PRIMARY KEY,
    module_id         BIGINT NOT NULL REFERENCES learning.modules (id) ON DELETE CASCADE,
    title             VARCHAR(255) NOT NULL,
    lesson_type       VARCHAR(40),
    duration_minutes  INTEGER,
    video_url         VARCHAR(500),
    downloadable      BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS learning.assignments (
    id           BIGSERIAL PRIMARY KEY,
    lesson_id    BIGINT NOT NULL REFERENCES learning.lessons (id) ON DELETE CASCADE,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(1000),
    due_date     DATE,
    max_score    INTEGER
);

CREATE TABLE IF NOT EXISTS learning.submissions (
    id              BIGSERIAL PRIMARY KEY,
    assignment_id   BIGINT NOT NULL REFERENCES learning.assignments (id) ON DELETE CASCADE,
    profile_id      BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    repository_url  VARCHAR(500),
    file_url        VARCHAR(500),
    score           INTEGER,
    status          VARCHAR(40) NOT NULL,
    submitted_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS learning.quizzes (
    id                 BIGSERIAL PRIMARY KEY,
    lesson_id          BIGINT NOT NULL REFERENCES learning.lessons (id) ON DELETE CASCADE,
    title              VARCHAR(255) NOT NULL,
    total_marks        INTEGER NOT NULL,
    passing_marks      INTEGER NOT NULL,
    duration_minutes   INTEGER
);

CREATE TABLE IF NOT EXISTS learning.quiz_attempts (
    id              BIGSERIAL PRIMARY KEY,
    quiz_id         BIGINT NOT NULL REFERENCES learning.quizzes (id) ON DELETE CASCADE,
    profile_id      BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    obtained_marks  INTEGER NOT NULL,
    passed          BOOLEAN NOT NULL,
    attempt_number  INTEGER NOT NULL DEFAULT 1,
    attempted_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS learning.enrollments (
    id                      BIGSERIAL PRIMARY KEY,
    profile_id              BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    course_id               BIGINT NOT NULL REFERENCES learning.courses (id) ON DELETE CASCADE,
    enrollment_date         DATE NOT NULL DEFAULT CURRENT_DATE,
    status                  VARCHAR(40) NOT NULL,
    completion_percentage   INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT uq_enrollment UNIQUE (profile_id, course_id)
);

CREATE TABLE IF NOT EXISTS learning.learning_progress (
    id                 BIGSERIAL PRIMARY KEY,
    enrollment_id      BIGINT NOT NULL REFERENCES learning.enrollments (id) ON DELETE CASCADE,
    lesson_id          BIGINT NOT NULL REFERENCES learning.lessons (id) ON DELETE CASCADE,
    completed          BOOLEAN NOT NULL DEFAULT FALSE,
    watch_percentage   INTEGER NOT NULL DEFAULT 0,
    completed_at       TIMESTAMPTZ
);

-- Domain 5: Projects
CREATE TABLE IF NOT EXISTS projects.projects (
    id                 BIGSERIAL PRIMARY KEY,
    profile_id         BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    course_id          BIGINT REFERENCES learning.courses (id) ON DELETE SET NULL,
    title              VARCHAR(255) NOT NULL,
    description        VARCHAR(2000),
    project_type       VARCHAR(80),
    difficulty_level   VARCHAR(40),
    status             VARCHAR(40) NOT NULL,
    start_date         DATE,
    end_date           DATE,
    github_repository  VARCHAR(500),
    created_at         TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS projects.project_milestones (
    id           BIGSERIAL PRIMARY KEY,
    project_id   BIGINT NOT NULL REFERENCES projects.projects (id) ON DELETE CASCADE,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(1000),
    sequence     INTEGER NOT NULL,
    due_date     DATE,
    completed    BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS projects.project_submissions (
    id                 BIGSERIAL PRIMARY KEY,
    project_id         BIGINT NOT NULL REFERENCES projects.projects (id) ON DELETE CASCADE,
    profile_id         BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    repository_url     VARCHAR(500),
    live_demo_url      VARCHAR(500),
    documentation_url  VARCHAR(500),
    submission_status  VARCHAR(40) NOT NULL,
    submitted_at       TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS projects.project_reviews (
    id                   BIGSERIAL PRIMARY KEY,
    submission_id        BIGINT NOT NULL REFERENCES projects.project_submissions (id) ON DELETE CASCADE,
    reviewer_id          BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE RESTRICT,
    technical_score      INTEGER,
    design_score         INTEGER,
    documentation_score  INTEGER,
    innovation_score     INTEGER,
    overall_score        INTEGER,
    feedback             VARCHAR(2000),
    reviewed_at          TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS projects.portfolio_items (
    id             BIGSERIAL PRIMARY KEY,
    profile_id     BIGINT NOT NULL REFERENCES user_profiles.profiles (id) ON DELETE CASCADE,
    project_id     BIGINT NOT NULL REFERENCES projects.projects (id) ON DELETE CASCADE,
    title          VARCHAR(255) NOT NULL,
    thumbnail_uri  VARCHAR(500),
    live_url       VARCHAR(500),
    github_url     VARCHAR(500),
    featured       BOOLEAN NOT NULL DEFAULT FALSE,
    views          INTEGER NOT NULL DEFAULT 0,
    likes          INTEGER NOT NULL DEFAULT 0,
    published_at   TIMESTAMPTZ
);

-- Seed Domain 2
INSERT INTO user_profiles.profiles (user_id, first_name, last_name, date_of_birth, gender, bio, profile_picture, city, state, country, linkedin_url, github_url)
SELECT u.id, v.first_name, v.last_name, v.dob, v.gender, v.bio, v.picture, v.city, v.state, v.country, v.linkedin, v.github
FROM (VALUES
    ('dean@apex-university.edu', 'Ananya', 'Iyer', DATE '1978-04-12', 'FEMALE', 'Dean of Engineering focused on industry-ready graduates.', '/pics/ananya.png', 'Mumbai', 'Maharashtra', 'India', 'https://linkedin.com/in/ananyaiyer', 'https://github.com/ananyaiyer'),
    ('mentor.kavya@apex-university.edu', 'Kavya', 'Nair', DATE '1990-09-03', 'FEMALE', 'Mentor for full-stack and cloud tracks.', '/pics/kavya.png', 'Pune', 'Maharashtra', 'India', 'https://linkedin.com/in/kavyanair', 'https://github.com/kavyanair'),
    ('evaluator.arjun@apex-university.edu', 'Arjun', 'Mehta', DATE '1988-01-21', 'MALE', 'Project evaluator and assessment specialist.', '/pics/arjun.png', 'Bengaluru', 'Karnataka', 'India', 'https://linkedin.com/in/arjunmehta', 'https://github.com/arjunmehta'),
    ('recruiter.anita@novatech.com', 'Anita', 'Sharma', DATE '1992-06-18', 'FEMALE', 'Technical recruiter for product engineering roles.', '/pics/anita.png', 'Hyderabad', 'Telangana', 'India', 'https://linkedin.com/in/anitasharma', 'https://github.com/anitasharma'),
    ('priya.student@apex-university.edu', 'Priya', 'Deshmukh', DATE '2003-11-09', 'FEMALE', 'Computer engineering student aiming for backend roles.', '/pics/priya.png', 'Mumbai', 'Maharashtra', 'India', 'https://linkedin.com/in/priyadeshmukh', 'https://github.com/priyadeshmukh'),
    ('rohan.student@apex-university.edu', 'Rohan', 'Patil', DATE '2002-02-14', 'MALE', 'Interested in data engineering and ML ops.', '/pics/rohan.png', 'Nagpur', 'Maharashtra', 'India', 'https://linkedin.com/in/rohanpatil', 'https://github.com/rohanpatil'),
    ('meera.student@apex-university.edu', 'Meera', 'Kulkarni', DATE '2004-07-27', 'FEMALE', 'Frontend-focused learner building a strong portfolio.', '/pics/meera.png', 'Pune', 'Maharashtra', 'India', 'https://linkedin.com/in/meerakulkarni', 'https://github.com/meerakulkarni'),
    ('vikram.student@apex-university.edu', 'Vikram', 'Joshi', DATE '2001-12-05', 'MALE', 'Working on cloud-native Java projects.', '/pics/vikram.png', 'Nashik', 'Maharashtra', 'India', 'https://linkedin.com/in/vikramjoshi', 'https://github.com/vikramjoshi')
) AS v(email, first_name, last_name, dob, gender, bio, picture, city, state, country, linkedin, github)
JOIN identity_authentication.users u ON u.email = v.email;

INSERT INTO user_profiles.resumes (profile_id, file_name, file_url, file_type, parsed_text, version, is_primary)
SELECT p.id, v.file_name, v.file_url, 'application/pdf', v.parsed_text, v.version, v.is_primary
FROM (VALUES
    ('priya.student@apex-university.edu', 'Priya_Deshmukh_Resume.pdf', '/resumes/priya_v2.pdf', 'Java Spring Boot intern with REST and PostgreSQL experience.', 2, TRUE),
    ('priya.student@apex-university.edu', 'Priya_Deshmukh_Resume_v1.pdf', '/resumes/priya_v1.pdf', 'Earlier campus resume.', 1, FALSE),
    ('rohan.student@apex-university.edu', 'Rohan_Patil_Resume.pdf', '/resumes/rohan.pdf', 'Python, SQL, and Spark coursework.', 1, TRUE),
    ('meera.student@apex-university.edu', 'Meera_Kulkarni_Resume.pdf', '/resumes/meera.pdf', 'React and UI engineering projects.', 1, TRUE),
    ('vikram.student@apex-university.edu', 'Vikram_Joshi_Resume.pdf', '/resumes/vikram.pdf', 'Docker, Kubernetes, and Java microservices.', 1, TRUE)
) AS v(email, file_name, file_url, parsed_text, version, is_primary)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO user_profiles.educations (profile_id, institute_name, degree, specialization, cgpa, passing_year, start_year, end_year, is_current)
SELECT p.id, 'Apex University', 'B.E.', 'Information Technology', v.cgpa, v.passing_year, 2021, v.end_year, v.is_current
FROM (VALUES
    ('priya.student@apex-university.edu', 8.70, 2026, 2026, TRUE),
    ('rohan.student@apex-university.edu', 8.20, 2025, 2025, TRUE),
    ('meera.student@apex-university.edu', 9.10, 2027, 2027, TRUE),
    ('vikram.student@apex-university.edu', 7.80, 2024, 2024, FALSE)
) AS v(email, cgpa, passing_year, end_year, is_current)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO user_profiles.educations (profile_id, institute_name, degree, specialization, cgpa, passing_year, start_year, end_year, is_current)
SELECT p.id, 'Apex University', 'M.Tech', 'Computer Science', 8.90, 2012, 2010, 2012, FALSE
FROM identity_authentication.users u
JOIN user_profiles.profiles p ON p.user_id = u.id
WHERE u.email = 'mentor.kavya@apex-university.edu';

INSERT INTO user_profiles.experiences (profile_id, company_name, designation, employment_type, start_date, end_date, currently_working, location, responsibilities)
SELECT p.id, v.company, v.designation, v.emp_type, v.start_date, v.end_date, v.current, v.location, v.resp
FROM (VALUES
    ('mentor.kavya@apex-university.edu', 'Apex University', 'Industry Mentor', 'FULL_TIME', DATE '2020-06-01', NULL, TRUE, 'Pune', 'Mentors students on full-stack delivery.'),
    ('evaluator.arjun@apex-university.edu', 'Apex University', 'Evaluator', 'FULL_TIME', DATE '2019-01-15', NULL, TRUE, 'Bengaluru', 'Reviews project submissions and assessments.'),
    ('recruiter.anita@novatech.com', 'NovaTech', 'Technical Recruiter', 'FULL_TIME', DATE '2021-03-01', NULL, TRUE, 'Hyderabad', 'Hires graduate engineers for product teams.'),
    ('priya.student@apex-university.edu', 'NovaTech', 'Java Intern', 'INTERNSHIP', DATE '2025-06-01', DATE '2025-08-31', FALSE, 'Mumbai', 'Built REST APIs with Spring Boot.'),
    ('vikram.student@apex-university.edu', 'CloudNest', 'DevOps Intern', 'INTERNSHIP', DATE '2024-01-10', DATE '2024-06-30', FALSE, 'Pune', 'Automated CI pipelines.')
) AS v(email, company, designation, emp_type, start_date, end_date, current, location, resp)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO user_profiles.certifications (profile_id, certification_name, issuer, credential_id, credential_url, issue_date, expiry_date)
SELECT p.id, v.name, v.issuer, v.cred, v.url, v.issued, v.expiry
FROM (VALUES
    ('priya.student@apex-university.edu', 'Oracle Java SE 17', 'Oracle', 'OCP-17-PRIYA', 'https://creds.oracle.com/priya', DATE '2025-03-12', DATE '2028-03-12'),
    ('rohan.student@apex-university.edu', 'Google Data Analytics', 'Google', 'GDA-ROHAN', 'https://creds.google.com/rohan', DATE '2025-01-20', NULL),
    ('meera.student@apex-university.edu', 'Meta Front-End Developer', 'Meta', 'META-MEERA', 'https://creds.meta.com/meera', DATE '2025-05-02', NULL),
    ('vikram.student@apex-university.edu', 'AWS Cloud Practitioner', 'Amazon', 'AWS-VIKRAM', 'https://aws.amazon.com/verification/vikram', DATE '2024-11-08', DATE '2027-11-08'),
    ('mentor.kavya@apex-university.edu', 'CKAD', 'CNCF', 'CKAD-KAVYA', 'https://www.cncf.io/kavya', DATE '2023-08-01', DATE '2026-08-01')
) AS v(email, name, issuer, cred, url, issued, expiry)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO user_profiles.career_goals (profile_id, target_role, target_industry, target_company, preferred_location, expected_ctc, employment_type, target_timeline_months)
SELECT p.id, v.role, v.industry, v.company, v.location, v.ctc, v.emp, v.months
FROM (VALUES
    ('priya.student@apex-university.edu', 'Backend Engineer', 'Product Software', 'NovaTech', 'Mumbai', 1200000, 'FULL_TIME', 6),
    ('rohan.student@apex-university.edu', 'Data Engineer', 'Analytics', 'InsightLabs', 'Pune', 1100000, 'FULL_TIME', 8),
    ('meera.student@apex-university.edu', 'Frontend Engineer', 'SaaS', 'PixelForge', 'Bengaluru', 1000000, 'FULL_TIME', 10),
    ('vikram.student@apex-university.edu', 'Cloud Engineer', 'Cloud Infrastructure', 'CloudNest', 'Hyderabad', 1300000, 'FULL_TIME', 4)
) AS v(email, role, industry, company, location, ctc, emp, months)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO user_profiles.skill_passports (profile_id, passport_score, total_skills, verified_skills, completed_courses, completed_projects, certificates_earned, verification_status)
SELECT p.id, v.score, v.total, v.verified, v.courses, v.projects, v.certs, v.status
FROM (VALUES
    ('priya.student@apex-university.edu', 78.50, 6, 3, 2, 2, 1, 'VERIFIED'),
    ('rohan.student@apex-university.edu', 71.00, 5, 2, 1, 1, 1, 'IN_REVIEW'),
    ('meera.student@apex-university.edu', 64.25, 4, 1, 1, 1, 1, 'PENDING'),
    ('vikram.student@apex-university.edu', 82.00, 7, 4, 3, 2, 1, 'VERIFIED'),
    ('mentor.kavya@apex-university.edu', 94.00, 12, 10, 8, 6, 4, 'VERIFIED')
) AS v(email, score, total, verified, courses, projects, certs, status)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

-- Seed Domain 3
INSERT INTO skills.skill_categories (category_name, description, icon, is_active) VALUES
('Programming', 'Core programming languages', 'code', TRUE),
('Backend', 'Server-side frameworks and APIs', 'server', TRUE),
('Frontend', 'User interface technologies', 'layout', TRUE),
('Data', 'Data engineering and analytics', 'database', TRUE),
('Cloud', 'Cloud platforms and DevOps', 'cloud', TRUE);

INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified)
SELECT c.id, v.skill_name, v.description, v.level, TRUE
FROM (VALUES
    ('Programming', 'Java', 'Object-oriented programming with Java', 'INTERMEDIATE'),
    ('Programming', 'Python', 'Scripting and data-oriented Python', 'BEGINNER'),
    ('Backend', 'Spring Boot', 'REST APIs and enterprise Java services', 'INTERMEDIATE'),
    ('Frontend', 'React', 'Component-based UI development', 'INTERMEDIATE'),
    ('Data', 'SQL', 'Relational querying and modeling', 'BEGINNER'),
    ('Data', 'Apache Spark', 'Distributed data processing', 'ADVANCED'),
    ('Cloud', 'Docker', 'Containerization fundamentals', 'INTERMEDIATE'),
    ('Cloud', 'Kubernetes', 'Container orchestration', 'ADVANCED')
) AS v(category_name, skill_name, description, level)
JOIN skills.skill_categories c ON c.category_name = v.category_name;

INSERT INTO skills.skill_assessments (skill_id, assessment_type, passing_score, total_marks, duration_minutes)
SELECT s.id, v.atype, v.pass, v.total, v.mins
FROM (VALUES
    ('Java', 'MCQ', 60, 100, 45),
    ('Spring Boot', 'PROJECT', 70, 100, 120),
    ('SQL', 'PRACTICAL', 65, 100, 40),
    ('React', 'MCQ', 60, 100, 30),
    ('Docker', 'LAB', 70, 100, 60)
) AS v(skill_name, atype, pass, total, mins)
JOIN skills.skills s ON s.skill_name = v.skill_name;

INSERT INTO skills.competencies (skill_id, competency_name, description, display_order)
SELECT s.id, v.cname, v.descr, v.ord
FROM (VALUES
    ('Java', 'Core Java', 'Language syntax and OOP', 1),
    ('Java', 'Collections', 'Lists, maps, and streams', 2),
    ('Spring Boot', 'REST APIs', 'Controllers and validation', 1),
    ('Spring Boot', 'Data JPA', 'Repositories and mapping', 2),
    ('SQL', 'Querying', 'Joins, filters, aggregates', 1),
    ('React', 'Components', 'State, props, and hooks', 1)
) AS v(skill_name, cname, descr, ord)
JOIN skills.skills s ON s.skill_name = v.skill_name;

INSERT INTO skills.competency_levels (competency_id, level_name, level_value, criteria)
SELECT c.id, v.lname, v.lval, v.criteria
FROM skills.competencies c
JOIN (VALUES
    ('Beginner', 1, 'Can explain concepts with guidance'),
    ('Intermediate', 2, 'Can implement standard tasks independently'),
    ('Advanced', 3, 'Can design and review production-quality work')
) AS v(lname, lval, criteria) ON TRUE;

INSERT INTO skills.user_skills (profile_id, skill_id, competency_level_id, skill_assessment_id, proficiency_score, verified, last_used)
SELECT p.id, s.id, cl.id, sa.id, v.score, v.verified, CURRENT_DATE - (v.days || ' days')::interval
FROM (VALUES
    ('priya.student@apex-university.edu', 'Java', 'Intermediate', 'Java', 78, TRUE, 3),
    ('priya.student@apex-university.edu', 'Spring Boot', 'Intermediate', 'Spring Boot', 74, TRUE, 5),
    ('priya.student@apex-university.edu', 'SQL', 'Beginner', 'SQL', 68, TRUE, 8),
    ('rohan.student@apex-university.edu', 'Python', 'Intermediate', NULL, 72, FALSE, 2),
    ('rohan.student@apex-university.edu', 'SQL', 'Intermediate', 'SQL', 80, TRUE, 4),
    ('meera.student@apex-university.edu', 'React', 'Beginner', 'React', 61, TRUE, 1),
    ('vikram.student@apex-university.edu', 'Docker', 'Advanced', 'Docker', 88, TRUE, 6),
    ('vikram.student@apex-university.edu', 'Java', 'Intermediate', 'Java', 76, TRUE, 10)
) AS v(email, skill_name, level_name, assessment_skill, score, verified, days)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN skills.skills s ON s.skill_name = v.skill_name
JOIN skills.competencies comp ON comp.skill_id = s.id AND comp.display_order = 1
JOIN skills.competency_levels cl ON cl.competency_id = comp.id AND cl.level_name = v.level_name
LEFT JOIN skills.skill_assessments sa ON v.assessment_skill IS NOT NULL AND sa.skill_id = (
    SELECT id FROM skills.skills WHERE skill_name = v.assessment_skill
);

INSERT INTO skills.skill_gaps (profile_id, skill_id, current_level, target_level, gap_score, priority)
SELECT p.id, s.id, v.current, v.target, v.gap, v.priority
FROM (VALUES
    ('priya.student@apex-university.edu', 'Kubernetes', 1, 3, 2, 'HIGH'),
    ('rohan.student@apex-university.edu', 'Apache Spark', 1, 3, 2, 'MEDIUM'),
    ('meera.student@apex-university.edu', 'Spring Boot', 1, 2, 1, 'MEDIUM'),
    ('vikram.student@apex-university.edu', 'Kubernetes', 2, 3, 1, 'HIGH')
) AS v(email, skill_name, current, target, gap, priority)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN skills.skills s ON s.skill_name = v.skill_name;

INSERT INTO skills.skill_recommendations (profile_id, skill_id, recommendation_reason, recommendation_score, recommendation_type)
SELECT p.id, s.id, v.reason, v.score, v.rtype
FROM (VALUES
    ('priya.student@apex-university.edu', 'Docker', 'Needed for backend deployment track', 86, 'CAREER_GOAL'),
    ('priya.student@apex-university.edu', 'Kubernetes', 'Closes cloud skill gap for target role', 91, 'SKILL_GAP'),
    ('rohan.student@apex-university.edu', 'Apache Spark', 'Aligns with data engineer career goal', 88, 'CAREER_GOAL'),
    ('meera.student@apex-university.edu', 'React', 'Strengthen frontend portfolio projects', 79, 'LEARNING_PATH')
) AS v(email, skill_name, reason, score, rtype)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN skills.skills s ON s.skill_name = v.skill_name;

-- Seed Domain 4
INSERT INTO learning.learning_paths (profile_id, title, description, difficulty, estimated_duration, status)
SELECT p.id, v.title, v.descr, v.diff, v.hours, 'ACTIVE'
FROM (VALUES
    ('priya.student@apex-university.edu', 'Java Backend Career Path', 'Spring Boot, APIs, and databases', 'INTERMEDIATE', 80),
    ('rohan.student@apex-university.edu', 'Data Engineering Path', 'SQL, Python, and Spark', 'INTERMEDIATE', 90),
    ('meera.student@apex-university.edu', 'Frontend Engineering Path', 'React and modern UI', 'BEGINNER', 60)
) AS v(email, title, descr, diff, hours)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO learning.learning_paths (title, description, difficulty, estimated_duration, status)
VALUES ('Cloud Native Java Path', 'Containers and orchestration for Java services', 'ADVANCED', 70, 'ACTIVE');

INSERT INTO learning.courses (learning_path_id, title, description, instructor, duration_hours, level, published)
SELECT lp.id, v.title, v.descr, v.instructor, v.hours, v.level, TRUE
FROM (VALUES
    ('Java Backend Career Path', 'Spring Boot Fundamentals', 'Build REST APIs with Spring Boot', 'Kavya Nair', 24, 'INTERMEDIATE'),
    ('Java Backend Career Path', 'PostgreSQL for Developers', 'Schema design and SQL in production', 'Arjun Mehta', 16, 'BEGINNER'),
    ('Data Engineering Path', 'SQL Mastery', 'Advanced querying for analytics', 'Arjun Mehta', 18, 'INTERMEDIATE'),
    ('Frontend Engineering Path', 'React Essentials', 'Components, hooks, and routing', 'Kavya Nair', 20, 'BEGINNER'),
    ('Cloud Native Java Path', 'Docker and Kubernetes', 'Package and run cloud-native apps', 'Kavya Nair', 22, 'ADVANCED')
) AS v(path_title, title, descr, instructor, hours, level)
JOIN learning.learning_paths lp ON lp.title = v.path_title;

INSERT INTO learning.modules (course_id, title, description, sequence)
SELECT c.id, v.title, v.descr, v.seq
FROM (VALUES
    ('Spring Boot Fundamentals', 'Getting Started', 'Project setup and first controller', 1),
    ('Spring Boot Fundamentals', 'Persistence', 'JPA entities and repositories', 2),
    ('PostgreSQL for Developers', 'Schema Design', 'Tables, keys, and indexes', 1),
    ('SQL Mastery', 'Joins and Aggregations', 'Multi-table analytics queries', 1),
    ('React Essentials', 'Components and State', 'Building interactive UIs', 1),
    ('Docker and Kubernetes', 'Containers', 'Images, volumes, and compose', 1)
) AS v(course, title, descr, seq)
JOIN learning.courses c ON c.title = v.course;

INSERT INTO learning.lessons (module_id, title, lesson_type, duration_minutes, video_url, downloadable)
SELECT m.id, v.title, v.ltype, v.mins, v.url, v.dl
FROM (VALUES
    ('Getting Started', 'Create a Spring Boot app', 'VIDEO', 25, 'https://learn.skillops.io/sb-01', TRUE),
    ('Persistence', 'Map entities to PostgreSQL', 'VIDEO', 30, 'https://learn.skillops.io/sb-02', TRUE),
    ('Schema Design', 'Primary and foreign keys', 'ARTICLE', 20, NULL, TRUE),
    ('Joins and Aggregations', 'Inner and outer joins lab', 'LAB', 35, NULL, FALSE),
    ('Components and State', 'useState and props', 'VIDEO', 28, 'https://learn.skillops.io/react-01', TRUE),
    ('Containers', 'Write a Dockerfile', 'LAB', 40, NULL, TRUE)
) AS v(module, title, ltype, mins, url, dl)
JOIN learning.modules m ON m.title = v.module;

INSERT INTO learning.assignments (lesson_id, title, description, due_date, max_score)
SELECT l.id, v.title, v.descr, CURRENT_DATE + v.due, 100
FROM (VALUES
    ('Create a Spring Boot app', 'Hello API', 'Expose a health endpoint', 14),
    ('Map entities to PostgreSQL', 'Identity schema mapping', 'Map users and roles', 21),
    ('useState and props', 'Profile card UI', 'Build a profile summary card', 10)
) AS v(lesson, title, descr, due)
JOIN learning.lessons l ON l.title = v.lesson;

INSERT INTO learning.quizzes (lesson_id, title, total_marks, passing_marks, duration_minutes)
SELECT l.id, v.title, 20, 12, 15
FROM (VALUES
    ('Create a Spring Boot app', 'Spring Boot Basics Quiz'),
    ('Primary and foreign keys', 'Schema Design Quiz'),
    ('useState and props', 'React State Quiz')
) AS v(lesson, title)
JOIN learning.lessons l ON l.title = v.lesson;

INSERT INTO learning.enrollments (profile_id, course_id, enrollment_date, status, completion_percentage)
SELECT p.id, c.id, CURRENT_DATE - 20, v.status, v.pct
FROM (VALUES
    ('priya.student@apex-university.edu', 'Spring Boot Fundamentals', 'IN_PROGRESS', 45),
    ('priya.student@apex-university.edu', 'PostgreSQL for Developers', 'COMPLETED', 100),
    ('rohan.student@apex-university.edu', 'SQL Mastery', 'IN_PROGRESS', 30),
    ('meera.student@apex-university.edu', 'React Essentials', 'IN_PROGRESS', 20),
    ('vikram.student@apex-university.edu', 'Docker and Kubernetes', 'IN_PROGRESS', 55)
) AS v(email, course, status, pct)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN learning.courses c ON c.title = v.course;

INSERT INTO learning.submissions (assignment_id, profile_id, repository_url, file_url, score, status)
SELECT a.id, p.id, v.repo, v.file_url, v.score, v.status
FROM (VALUES
    ('Hello API', 'priya.student@apex-university.edu', 'https://github.com/priyadeshmukh/hello-api', '/subs/priya-hello.zip', 88, 'GRADED'),
    ('Identity schema mapping', 'priya.student@apex-university.edu', 'https://github.com/priyadeshmukh/identity-jpa', '/subs/priya-jpa.zip', NULL, 'SUBMITTED'),
    ('Profile card UI', 'meera.student@apex-university.edu', 'https://github.com/meerakulkarni/profile-card', '/subs/meera-ui.zip', 91, 'GRADED')
) AS v(assignment, email, repo, file_url, score, status)
JOIN learning.assignments a ON a.title = v.assignment
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO learning.quiz_attempts (quiz_id, profile_id, obtained_marks, passed, attempt_number)
SELECT q.id, p.id, v.marks, v.passed, v.attempt
FROM (VALUES
    ('Spring Boot Basics Quiz', 'priya.student@apex-university.edu', 16, TRUE, 1),
    ('Schema Design Quiz', 'priya.student@apex-university.edu', 14, TRUE, 1),
    ('React State Quiz', 'meera.student@apex-university.edu', 11, FALSE, 1),
    ('React State Quiz', 'meera.student@apex-university.edu', 15, TRUE, 2)
) AS v(quiz, email, marks, passed, attempt)
JOIN learning.quizzes q ON q.title = v.quiz
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id;

INSERT INTO learning.learning_progress (enrollment_id, lesson_id, completed, watch_percentage, completed_at)
SELECT e.id, l.id, v.completed, v.watch, CASE WHEN v.completed THEN NOW() - INTERVAL '2 days' ELSE NULL END
FROM (VALUES
    ('priya.student@apex-university.edu', 'Spring Boot Fundamentals', 'Create a Spring Boot app', TRUE, 100),
    ('priya.student@apex-university.edu', 'Spring Boot Fundamentals', 'Map entities to PostgreSQL', FALSE, 40),
    ('meera.student@apex-university.edu', 'React Essentials', 'useState and props', FALSE, 25),
    ('vikram.student@apex-university.edu', 'Docker and Kubernetes', 'Write a Dockerfile', TRUE, 100)
) AS v(email, course, lesson, completed, watch)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN learning.courses c ON c.title = v.course
JOIN learning.enrollments e ON e.profile_id = p.id AND e.course_id = c.id
JOIN learning.lessons l ON l.title = v.lesson;

-- Seed Domain 5
INSERT INTO projects.projects (profile_id, course_id, title, description, project_type, difficulty_level, status, start_date, end_date, github_repository)
SELECT p.id, c.id, v.title, v.descr, v.ptype, v.diff, v.status, CURRENT_DATE - 30, v.end_date, v.repo
FROM (VALUES
    ('priya.student@apex-university.edu', 'Spring Boot Fundamentals', 'Campus Job Board API', 'REST API for campus placements', 'BACKEND', 'INTERMEDIATE', 'IN_PROGRESS', NULL, 'https://github.com/priyadeshmukh/job-board'),
    ('meera.student@apex-university.edu', 'React Essentials', 'Skill Passport UI', 'Frontend for digital skill passport', 'FRONTEND', 'BEGINNER', 'COMPLETED', CURRENT_DATE - 5, 'https://github.com/meerakulkarni/skill-passport-ui'),
    ('vikram.student@apex-university.edu', 'Docker and Kubernetes', 'Containerized SkillOps', 'Dockerize the SkillOps backend', 'DEVOPS', 'ADVANCED', 'IN_PROGRESS', NULL, 'https://github.com/vikramjoshi/skillops-k8s'),
    ('rohan.student@apex-university.edu', 'SQL Mastery', 'Placement Analytics Warehouse', 'Dimensional model for placement KPIs', 'DATA', 'INTERMEDIATE', 'SUBMITTED', CURRENT_DATE - 2, 'https://github.com/rohanpatil/placement-dw')
) AS v(email, course, title, descr, ptype, diff, status, end_date, repo)
JOIN identity_authentication.users u ON u.email = v.email
JOIN user_profiles.profiles p ON p.user_id = u.id
JOIN learning.courses c ON c.title = v.course;

INSERT INTO projects.project_milestones (project_id, title, description, sequence, due_date, completed)
SELECT pr.id, v.title, v.descr, v.seq, CURRENT_DATE + v.due, v.done
FROM (VALUES
    ('Campus Job Board API', 'Schema', 'Design identity and jobs tables', 1, -20, TRUE),
    ('Campus Job Board API', 'APIs', 'Implement CRUD endpoints', 2, 5, FALSE),
    ('Skill Passport UI', 'Wireframes', 'Design profile and skills screens', 1, -15, TRUE),
    ('Skill Passport UI', 'Implementation', 'Build React components', 2, -6, TRUE),
    ('Containerized SkillOps', 'Dockerfile', 'Package Spring Boot jar', 1, -10, TRUE),
    ('Placement Analytics Warehouse', 'Star schema', 'Facts and dimensions', 1, -8, TRUE)
) AS v(project, title, descr, seq, due, done)
JOIN projects.projects pr ON pr.title = v.project;

INSERT INTO projects.project_submissions (project_id, profile_id, repository_url, live_demo_url, documentation_url, submission_status)
SELECT pr.id, pr.profile_id, pr.github_repository, v.demo, v.docs, v.status
FROM (VALUES
    ('Skill Passport UI', 'https://passport.skillops.io/meera', 'https://docs.skillops.io/meera', 'SUBMITTED'),
    ('Placement Analytics Warehouse', NULL, 'https://docs.skillops.io/rohan', 'SUBMITTED'),
    ('Campus Job Board API', NULL, 'https://docs.skillops.io/priya', 'DRAFT')
) AS v(project, demo, docs, status)
JOIN projects.projects pr ON pr.title = v.project;

INSERT INTO projects.project_reviews (submission_id, reviewer_id, technical_score, design_score, documentation_score, innovation_score, overall_score, feedback)
SELECT s.id, rp.id, 18, 16, 17, 15, 16, 'Strong UI structure; add accessibility checks.'
FROM projects.project_submissions s
JOIN projects.projects pr ON pr.id = s.project_id
JOIN identity_authentication.users ru ON ru.email = 'evaluator.arjun@apex-university.edu'
JOIN user_profiles.profiles rp ON rp.user_id = ru.id
WHERE pr.title = 'Skill Passport UI';

INSERT INTO projects.project_reviews (submission_id, reviewer_id, technical_score, design_score, documentation_score, innovation_score, overall_score, feedback)
SELECT s.id, rp.id, 17, 14, 16, 16, 16, 'Solid warehouse model; document SCD handling.'
FROM projects.project_submissions s
JOIN projects.projects pr ON pr.id = s.project_id
JOIN identity_authentication.users ru ON ru.email = 'mentor.kavya@apex-university.edu'
JOIN user_profiles.profiles rp ON rp.user_id = ru.id
WHERE pr.title = 'Placement Analytics Warehouse';

INSERT INTO projects.portfolio_items (profile_id, project_id, title, thumbnail_uri, live_url, github_url, featured, views, likes, published_at)
SELECT pr.profile_id, pr.id, pr.title, v.thumb, v.live, pr.github_repository, v.featured, v.views, v.likes, NOW() - INTERVAL '3 days'
FROM (VALUES
    ('Skill Passport UI', '/thumbs/passport.png', 'https://passport.skillops.io/meera', TRUE, 240, 31),
    ('Placement Analytics Warehouse', '/thumbs/warehouse.png', NULL, FALSE, 88, 9)
) AS v(project, thumb, live, featured, views, likes)
JOIN projects.projects pr ON pr.title = v.project;
