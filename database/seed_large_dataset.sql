-- ============================================================================
-- SkillOps AI-Powered Workforce Development & Hiring Operating System
-- Comprehensive Seed Dataset across all 10 Domains (62 Tables)
-- ============================================================================
BEGIN;

-- Domain 1: Identity & Authentication
INSERT INTO identity_authentication.roles (name, description, created_at) VALUES ('PLATFORM_ADMIN', 'Full platform administration rights', NOW()) ON CONFLICT (name) DO NOTHING;
INSERT INTO identity_authentication.roles (name, description, created_at) VALUES ('ENTERPRISE_RECRUITER', 'Corporate recruiter managing jobs, candidates, and offers', NOW()) ON CONFLICT (name) DO NOTHING;
INSERT INTO identity_authentication.roles (name, description, created_at) VALUES ('TECHNICAL_EVALUATOR', 'Assesses candidates through interviews and code reviews', NOW()) ON CONFLICT (name) DO NOTHING;
INSERT INTO identity_authentication.roles (name, description, created_at) VALUES ('INSTRUCTOR_MENTOR', 'Creates learning paths, mentors students, and grades assignments', NOW()) ON CONFLICT (name) DO NOTHING;
INSERT INTO identity_authentication.roles (name, description, created_at) VALUES ('CANDIDATE_JOBSEEKER', 'Learner, assessment taker, and job applicant', NOW()) ON CONFLICT (name) DO NOTHING;
INSERT INTO identity_authentication.roles (name, description, created_at) VALUES ('HIRING_MANAGER', 'Reviews candidate scorecards and makes hiring decisions', NOW()) ON CONFLICT (name) DO NOTHING;
INSERT INTO identity_authentication.roles (name, description, created_at) VALUES ('CURRICULUM_LEAD', 'Designs courses, modules, and competency standards', NOW()) ON CONFLICT (name) DO NOTHING;

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'arjun.sharma@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543210', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'priya.patel@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543211', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'alex.chen@techcorp.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+14155552671', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'sarah.jenkins@cloudnet.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+12065551982', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'rohit.verma@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543212', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'ananya.sen@datascience.org', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543213', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'david.kim@fintech.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+14085557731', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'elena.rostova@cybersec.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+442079460192', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'michael.brown@enterprise.net', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+13125559021', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'neha.gupta@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543214', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'marcus.vance@ai-labs.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+15125553389', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'sneha.reddy@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543215', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'vikram.malhotra@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543216', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'emily.watson@talentflow.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+441632960114', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'rajesh.kumar@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543217', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'rachel.green@recruitment.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+12125554567', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'carlos.silva@techbrasil.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+551198765432', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'tanya.bhatia@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543218', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'kevin.zhang@siliconvalley.org', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+16505558812', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

    INSERT INTO identity_authentication.users (tenant_id, email, password_hash, phone, status, email_verified, created_at, updated_at)
    VALUES (1, 'pooja.nair@skillops.io', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '+919876543219', 'ACTIVE', true, NOW() - INTERVAL '30 days', NOW())
    ON CONFLICT (email) DO NOTHING;
    

INSERT INTO identity_authentication.user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW()
FROM identity_authentication.users u
CROSS JOIN identity_authentication.roles r
WHERE u.email IN ('arjun.sharma@skillops.io', 'alex.chen@techcorp.com', 'david.kim@fintech.io', 'ananya.sen@datascience.org')
  AND r.name = 'CANDIDATE_JOBSEEKER'
ON CONFLICT (user_id, role_id) DO NOTHING;

INSERT INTO identity_authentication.user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW()
FROM identity_authentication.users u
CROSS JOIN identity_authentication.roles r
WHERE u.email IN ('priya.patel@skillops.io', 'emily.watson@talentflow.com', 'rachel.green@recruitment.com')
  AND r.name = 'ENTERPRISE_RECRUITER'
ON CONFLICT (user_id, role_id) DO NOTHING;

INSERT INTO identity_authentication.user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW()
FROM identity_authentication.users u
CROSS JOIN identity_authentication.roles r
WHERE u.email IN ('sarah.jenkins@cloudnet.io', 'michael.brown@enterprise.net')
  AND r.name = 'TECHNICAL_EVALUATOR'
ON CONFLICT (user_id, role_id) DO NOTHING;


INSERT INTO identity_authentication.sessions (user_id, access_token, refresh_token, ip_address, device, expires_at)
SELECT u.id, md5(u.email || 'access' || random()::text), md5(u.email || 'refresh' || random()::text), '192.168.1.' || (10 + u.id % 200), 'MacBook Pro / Chrome 124', NOW() + INTERVAL '24 hours'
FROM identity_authentication.users u
WHERE u.id NOT IN (SELECT user_id FROM identity_authentication.sessions)
LIMIT 15;

INSERT INTO identity_authentication.refresh_tokens (user_id, token, expiry_date)
SELECT u.id, 'rt_' || md5(u.email || clock_timestamp()::text), NOW() + INTERVAL '30 days'
FROM identity_authentication.users u
WHERE u.id NOT IN (SELECT user_id FROM identity_authentication.refresh_tokens)
LIMIT 15;

INSERT INTO identity_authentication.login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT u.id, NOW() - (INTERVAL '1 day' * (u.id % 10)), NOW() - (INTERVAL '1 day' * (u.id % 10)) + INTERVAL '2 hours', 'Desktop', 'Chrome 124', '10.0.4.' || (u.id % 250)
FROM identity_authentication.users u
LIMIT 20;

INSERT INTO identity_authentication.audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT u.id, 'PROFILE_SYNC', 'Profile', u.id, '{"status":"PENDING"}', '{"status":"VERIFIED"}', NOW()
FROM identity_authentication.users u
LIMIT 15;

-- Domain 2: User Profiles

INSERT INTO user_profiles.profiles (user_id, first_name, last_name, date_of_birth, gender, bio, profile_picture, city, state, country, linkedin_url, github_url, created_at, updated_at)
SELECT 
    u.id,
    split_part(split_part(u.email, '@', 1), '.', 1),
    COALESCE(NULLIF(split_part(split_part(u.email, '@', 1), '.', 2), ''), 'Tech'),
    '1995-05-15'::date,
    'MALE',
    'Experienced technology professional focused on scalable enterprise architecture, automated testing, and high-reliability cloud platforms.',
    'https://assets.skillops.io/avatars/' || u.id || '.jpg',
    CASE (u.id % 6)
        WHEN 0 THEN 'Bangalore' WHEN 1 THEN 'San Francisco' WHEN 2 THEN 'London'
        WHEN 3 THEN 'Seattle' WHEN 4 THEN 'Hyderabad' ELSE 'Berlin'
    END,
    'Tech Hub',
    CASE (u.id % 4) WHEN 0 THEN 'India' WHEN 1 THEN 'USA' WHEN 2 THEN 'UK' ELSE 'Germany' END,
    'https://linkedin.com/in/' || split_part(u.email, '@', 1),
    'https://github.com/' || split_part(u.email, '@', 1),
    NOW() - INTERVAL '20 days',
    NOW()
FROM identity_authentication.users u
WHERE u.id NOT IN (SELECT user_id FROM user_profiles.profiles)
ON CONFLICT (user_id) DO NOTHING;


INSERT INTO user_profiles.resumes (profile_id, file_name, file_url, file_type, parsed_text, version, is_primary, uploaded_at)
SELECT p.id, p.first_name || '_Resume_2026.pdf', 'https://s3.amazonaws.com/skillops-resumes/' || p.id || '/resume.pdf', 'PDF', 'Comprehensive technical resume highlighting distributed systems, Spring Boot 3, and full-stack software development.', 1, true, NOW()
FROM user_profiles.profiles p
WHERE p.id NOT IN (SELECT profile_id FROM user_profiles.resumes)
LIMIT 20;

INSERT INTO user_profiles.educations (profile_id, institute_name, degree, specialization, cgpa, passing_year, start_year, end_year, is_current)
SELECT p.id,
    CASE (p.id % 4) WHEN 0 THEN 'Indian Institute of Technology (IIT)' WHEN 1 THEN 'Stanford University' WHEN 2 THEN 'BITS Pilani' ELSE 'University of California, Berkeley' END,
    CASE (p.id % 2) WHEN 0 THEN 'Bachelor of Technology' ELSE 'Master of Science' END,
    'Computer Science and Engineering', 8.85, 2021, 2017, 2021, false
FROM user_profiles.profiles p
WHERE p.id NOT IN (SELECT profile_id FROM user_profiles.educations)
LIMIT 20;

INSERT INTO user_profiles.experiences (profile_id, company_name, designation, employment_type, start_date, end_date, currently_working, location, responsibilities)
SELECT p.id,
    CASE (p.id % 5) WHEN 0 THEN 'Google' WHEN 1 THEN 'Amazon Web Services' WHEN 2 THEN 'Microsoft' WHEN 3 THEN 'Stripe' ELSE 'Netflix' END,
    'Senior Software Engineer', 'FULL_TIME', '2022-01-15'::date, NULL, true, 'Remote / Hybrid', 'Architected scalable cloud microservices, reduced API latency by 45%, and mentored junior engineers.'
FROM user_profiles.profiles p
WHERE p.id NOT IN (SELECT profile_id FROM user_profiles.experiences)
LIMIT 20;

INSERT INTO user_profiles.certifications (profile_id, certification_name, issuer, credential_id, credential_url, issue_date, expiry_date)
SELECT p.id,
    CASE (p.id % 4)
        WHEN 0 THEN 'AWS Certified Solutions Architect - Professional'
        WHEN 1 THEN 'Certified Kubernetes Administrator (CKA)'
        WHEN 2 THEN 'Google Cloud Professional Cloud Architect'
        ELSE 'Spring Certified Professional Developer'
    END,
    'Cloud Native Computing Foundation / Cloud Providers', 'CERT-' || md5(p.id::text || 'cert'), 'https://verify.skillops.io/cert/' || p.id, '2023-06-01'::date, '2026-06-01'::date
FROM user_profiles.profiles p
WHERE p.id NOT IN (SELECT profile_id FROM user_profiles.certifications)
LIMIT 20;

INSERT INTO user_profiles.career_goals (profile_id, target_role, target_industry, target_company, preferred_location, expected_ctc, employment_type, target_timeline_months, created_at)
SELECT p.id,
    'Principal Distributed Systems Engineer', 'Enterprise SaaS & FinTech', 'Stripe / Google', 'San Francisco, CA or Remote', 180000.00, 'FULL_TIME', 6, NOW()
FROM user_profiles.profiles p
WHERE p.id NOT IN (SELECT profile_id FROM user_profiles.career_goals)
LIMIT 20;

INSERT INTO user_profiles.skill_passports (profile_id, passport_score, total_skills, verified_skills, completed_courses, completed_projects, certificates_earned, verification_status, last_updated)
SELECT p.id, 840 + (p.id % 130), 16, 12, 5, 4, 3, 'VERIFIED', NOW()
FROM user_profiles.profiles p
WHERE p.id NOT IN (SELECT profile_id FROM user_profiles.skill_passports)
ON CONFLICT (profile_id) DO NOTHING;

-- Domain 3: Skills
INSERT INTO skills.skill_categories (category_name, description, icon, is_active, created_at) VALUES ('Backend Engineering', 'Core server-side development, frameworks, and protocols', 'server', true, NOW()) ON CONFLICT (category_name) DO NOTHING;
INSERT INTO skills.skill_categories (category_name, description, icon, is_active, created_at) VALUES ('Frontend & Web UI', 'Browser technologies, component frameworks, and responsive design', 'layout', true, NOW()) ON CONFLICT (category_name) DO NOTHING;
INSERT INTO skills.skill_categories (category_name, description, icon, is_active, created_at) VALUES ('Cloud Infrastructure & SRE', 'Cloud platforms, containerization, orchestration, and infrastructure as code', 'cloud', true, NOW()) ON CONFLICT (category_name) DO NOTHING;
INSERT INTO skills.skill_categories (category_name, description, icon, is_active, created_at) VALUES ('Data Engineering & AI', 'Big data pipelines, data warehouses, ML models, and GenAI', 'cpu', true, NOW()) ON CONFLICT (category_name) DO NOTHING;
INSERT INTO skills.skill_categories (category_name, description, icon, is_active, created_at) VALUES ('Security & Compliance', 'Application security, cryptography, identity management, and compliance standards', 'shield', true, NOW()) ON CONFLICT (category_name) DO NOTHING;
INSERT INTO skills.skill_categories (category_name, description, icon, is_active, created_at) VALUES ('Database Management', 'Relational and NoSQL storage engines, query tuning, and sharding', 'database', true, NOW()) ON CONFLICT (category_name) DO NOTHING;

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Java', 'Production-grade competency in Java', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Backend Engineering'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Spring Boot', 'Production-grade competency in Spring Boot', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Backend Engineering'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Go (Golang)', 'Production-grade competency in Go (Golang)', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Backend Engineering'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Python', 'Production-grade competency in Python', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Backend Engineering'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Microservices Architecture', 'Production-grade competency in Microservices Architecture', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Backend Engineering'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'React', 'Production-grade competency in React', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Frontend & Web UI'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'TypeScript', 'Production-grade competency in TypeScript', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Frontend & Web UI'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Next.js', 'Production-grade competency in Next.js', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Frontend & Web UI'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Docker', 'Production-grade competency in Docker', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Cloud Infrastructure & SRE'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Kubernetes', 'Production-grade competency in Kubernetes', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Cloud Infrastructure & SRE'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Terraform', 'Production-grade competency in Terraform', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Cloud Infrastructure & SRE'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'AWS', 'Production-grade competency in AWS', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Cloud Infrastructure & SRE'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Apache Kafka', 'Production-grade competency in Apache Kafka', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Data Engineering & AI'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'PyTorch', 'Production-grade competency in PyTorch', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Data Engineering & AI'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Large Language Models (LLMs)', 'Production-grade competency in Large Language Models (LLMs)', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Data Engineering & AI'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'PostgreSQL', 'Production-grade competency in PostgreSQL', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Database Management'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'Redis', 'Production-grade competency in Redis', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Database Management'
    ON CONFLICT DO NOTHING;
    

    INSERT INTO skills.skills (category_id, skill_name, description, difficulty_level, is_verified, created_at)
    SELECT id, 'OAuth2 & OpenID Connect', 'Production-grade competency in OAuth2 & OpenID Connect', 'ADVANCED', true, NOW()
    FROM skills.skill_categories
    WHERE category_name = 'Security & Compliance'
    ON CONFLICT DO NOTHING;
    

INSERT INTO skills.competencies (skill_id, competency_name, description, display_order)
SELECT s.id, s.skill_name || ' Core Implementation', 'Demonstrated production experience applying ' || s.skill_name, 1
FROM skills.skills s
WHERE s.id NOT IN (SELECT skill_id FROM skills.competencies)
LIMIT 15;

INSERT INTO skills.competency_levels (competency_id, level_name, level_value, criteria)
SELECT c.id, 'Expert Level', 4, 'Proven capacity to architect and optimize production workloads independently.'
FROM skills.competencies c
WHERE c.id NOT IN (SELECT competency_id FROM skills.competency_levels)
LIMIT 15;

INSERT INTO skills.skill_assessments (skill_id, assessment_type, passing_score, total_marks, duration_minutes)
SELECT s.id, 'TECHNICAL_MCQ_AND_CODING', 70, 100, 45
FROM skills.skills s
WHERE s.id NOT IN (SELECT skill_id FROM skills.skill_assessments)
LIMIT 12;

INSERT INTO skills.user_skills (profile_id, skill_id, competency_level_id, skill_assessment_id, proficiency_score, verified, last_used, updated_at)
SELECT p.id, s.id, cl.id, sa.id, 88.5, true, '2026-03-01'::date, NOW()
FROM user_profiles.profiles p
CROSS JOIN skills.skills s
LEFT JOIN skills.competencies c ON c.skill_id = s.id
LEFT JOIN skills.competency_levels cl ON cl.competency_id = c.id
LEFT JOIN skills.skill_assessments sa ON sa.skill_id = s.id
WHERE (p.id + s.id) % 3 = 0
ON CONFLICT (profile_id, skill_id) DO NOTHING;

INSERT INTO skills.skill_gaps (profile_id, skill_id, current_level, target_level, gap_score, priority)
SELECT p.id, s.id, 2, 4, 2, 'HIGH'
FROM user_profiles.profiles p
CROSS JOIN skills.skills s
WHERE (p.id + s.id) % 7 = 0
LIMIT 15;

INSERT INTO skills.skill_recommendations (profile_id, skill_id, recommendation_reason, recommendation_score, recommendation_type, generated_at)
SELECT p.id, s.id, 'High-demand skill matching your career target for Principal Engineer', 94.50, 'CAREER_ACCELERATOR', NOW()
FROM user_profiles.profiles p
CROSS JOIN skills.skills s
WHERE (p.id + s.id) % 5 = 0
LIMIT 15;

-- Domain 4: Learning

INSERT INTO learning.learning_paths (profile_id, title, description, difficulty, estimated_duration, status, created_at)
SELECT 
    p.id,
    CASE (p.id % 3)
        WHEN 0 THEN 'Enterprise Cloud-Native Java Architect'
        WHEN 1 THEN 'GenAI & Machine Learning Systems Specialist'
        ELSE 'Senior SRE & DevOps Platform Engineer'
    END,
    'End-to-end industry curriculum designed with deep real-world labs and capstones.',
    'ADVANCED',
    120,
    'PUBLISHED',
    NOW()
FROM user_profiles.profiles p
LIMIT 6;

INSERT INTO learning.courses (learning_path_id, title, description, instructor, duration_hours, level, published)
SELECT lp.id, lp.title || ' Masterclass', 'Comprehensive deep dive into modern production practices.', 'Dr. Jane Thornton', 36, 'ADVANCED', true
FROM learning.learning_paths lp
LIMIT 10;

INSERT INTO learning.modules (course_id, title, description, sequence)
SELECT c.id, 'Module 1: Architecture Foundations', 'Core concepts and component decomposition.', 1
FROM learning.courses c
LIMIT 10;

INSERT INTO learning.modules (course_id, title, description, sequence)
SELECT c.id, 'Module 2: High Availability & Scaling', 'Reliability, caching, and resiliency patterns.', 2
FROM learning.courses c
LIMIT 10;

INSERT INTO learning.lessons (module_id, title, lesson_type, duration_minutes, video_url, downloadable)
SELECT m.id, 'Lesson ' || m.sequence || '.1: Core Principles', 'VIDEO', 30, 'https://video.skillops.io/lessons/' || m.id || '/lesson1.mp4', true
FROM learning.modules m
LIMIT 20;

INSERT INTO learning.assignments (lesson_id, title, description, due_date, max_score)
SELECT l.id, 'Capstone Lab for ' || l.title, 'Build the required service with 95%+ test coverage.', NOW() + INTERVAL '14 days', 100
FROM learning.lessons l
LIMIT 12;

INSERT INTO learning.submissions (assignment_id, profile_id, repository_url, file_url, score, status, submitted_at)
SELECT a.id, p.id, 'https://github.com/' || p.first_name || '/lab-submission', 'https://s3.amazonaws.com/skillops-subs/' || a.id || '.zip', 95, 'GRADED', NOW()
FROM learning.assignments a
JOIN user_profiles.profiles p ON p.id = a.id
LIMIT 10;

INSERT INTO learning.quizzes (lesson_id, title, total_marks, passing_marks, duration_minutes)
SELECT l.id, l.title || ' Quiz', 20, 15, 15
FROM learning.lessons l
LIMIT 10;

INSERT INTO learning.quiz_attempts (quiz_id, profile_id, obtained_marks, passed, attempt_number, attempted_at)
SELECT q.id, p.id, 18, true, 1, NOW()
FROM learning.quizzes q
JOIN user_profiles.profiles p ON p.id = q.id
LIMIT 10;

INSERT INTO learning.enrollments (profile_id, course_id, enrollment_date, status, completion_percentage)
SELECT p.id, c.id, NOW() - INTERVAL '10 days', 'IN_PROGRESS', 72
FROM user_profiles.profiles p
CROSS JOIN learning.courses c
WHERE (p.id + c.id) % 4 = 0
ON CONFLICT (profile_id, course_id) DO NOTHING;

INSERT INTO learning.learning_progress (enrollment_id, lesson_id, completed, watch_percentage, completed_at)
SELECT e.id, l.id, true, 100, NOW() - INTERVAL '2 days'
FROM learning.enrollments e
JOIN learning.modules m ON m.course_id = e.course_id
JOIN learning.lessons l ON l.module_id = m.id
LIMIT 20;

-- Domain 5: Projects

INSERT INTO projects.projects (profile_id, course_id, title, description, project_type, difficulty_level, status, start_date, end_date, github_repository, created_at)
SELECT 
    p.id,
    1,
    CASE (p.id % 4)
        WHEN 0 THEN 'Distributed High-Throughput Event Ingestion Engine'
        WHEN 1 THEN 'Multi-Tenant Microservices E-Commerce Backplane'
        WHEN 2 THEN 'AI-Powered Resume Screener with Vector Embeddings'
        ELSE 'Cloud-Native Observability Dashboard and Telemetry Agent'
    END,
    'Production-grade implementation featuring resilience patterns, automated CI/CD pipeline, and 99.9% uptime SLA.',
    'CAPSTONE',
    'ADVANCED',
    'COMPLETED',
    '2025-11-01'::date,
    '2026-01-15'::date,
    'https://github.com/skillops-projects/portfolio-' || p.id,
    NOW()
FROM user_profiles.profiles p
LIMIT 12;

INSERT INTO projects.project_milestones (project_id, title, description, sequence, due_date, completed)
SELECT pr.id, 'Milestone 1: Domain Modeling & Database Schema', 'ER design, Liquibase/Flyway migrations, and test harness setup.', 1, NOW() - INTERVAL '20 days', true
FROM projects.projects pr
LIMIT 12;

INSERT INTO projects.project_submissions (project_id, profile_id, repository_url, live_demo_url, documentation_url, submission_status, submitted_at)
SELECT pr.id, pr.profile_id, pr.github_repository, 'https://demo.skillops.io/projects/' || pr.id, 'https://docs.skillops.io/projects/' || pr.id, 'ACCEPTED', NOW()
FROM projects.projects pr
LIMIT 10;

INSERT INTO projects.project_reviews (submission_id, reviewer_id, technical_score, design_score, documentation_score, innovation_score, overall_score, feedback, reviewed_at)
SELECT ps.id, 1, 95.0, 92.0, 90.0, 96.0, 93.5, 'Outstanding architecture, comprehensive integration tests, clean code style.', NOW()
FROM projects.project_submissions ps
LIMIT 10;

INSERT INTO projects.portfolio_items (profile_id, project_id, title, thumbnail_uri, live_url, github_url, featured, views, likes, published_at)
SELECT pr.profile_id, pr.id, pr.title, 'https://assets.skillops.io/thumbnails/' || pr.id || '.png', 'https://demo.skillops.io/projects/' || pr.id, pr.github_repository, true, 420, 88, NOW()
FROM projects.projects pr
LIMIT 8;

-- Domain 6: Assessments

INSERT INTO assessments.assessments (title, assessment_type, difficulty, total_marks, duration_minutes, passing_marks, is_published, created_at)
VALUES 
('Senior Java & Spring Boot Core Assessment Benchmark', 'MCQ_AND_CODING', 'ADVANCED', 100, 60, 70, true, NOW()),
('Distributed Systems & Concurrency Master Benchmark', 'CODING_CHALLENGE', 'EXPERT', 100, 90, 75, true, NOW()),
('PostgreSQL Query Optimization & Indexing Benchmark', 'HANDS_ON', 'INTERMEDIATE', 50, 45, 35, true, NOW()),
('Kubernetes & Cloud Infrastructure Production Challenge', 'PRACTICAL', 'ADVANCED', 100, 60, 70, true, NOW());

INSERT INTO assessments.assessment_questions (assessment_id, question_type, question, marks, sequence)
SELECT a.id, 'MCQ', 'In Spring Boot, how does the Transactional annotation propagate context across asynchronous worker threads?', 10, 1
FROM assessments.assessments a
LIMIT 8;

INSERT INTO assessments.coding_challenges (assessment_id, title, language, difficulty, max_score)
SELECT a.id, 'High-Throughput Leaky Bucket Rate Limiter', 'JAVA', 'HARD', 50
FROM assessments.assessments a
LIMIT 6;

INSERT INTO assessments.assessment_attempts (assessment_id, profile_id, started_at, submitted_at, obtained_marks, passed, status)
SELECT a.id, p.id, NOW() - INTERVAL '3 hours', NOW() - INTERVAL '2 hours', 88, true, 'EVALUATED'
FROM assessments.assessments a
JOIN user_profiles.profiles p ON p.id = a.id
LIMIT 8;

INSERT INTO assessments.coding_submissions (challenge_id, profile_id, repository_url, source_code_url, execution_score, quality_score, submitted_at)
SELECT cc.id, p.id, 'https://github.com/skillops-eval/sol-' || p.id, 'https://s3.amazonaws.com/code/' || p.id || '.java', 98.0, 94.0, NOW()
FROM assessments.coding_challenges cc
JOIN user_profiles.profiles p ON p.id = cc.id
LIMIT 6;

INSERT INTO assessments.evaluation_results (attempt_id, coding_submission_id, objective_score, coding_score, ai_score, final_score, remarks)
SELECT aa.id, cs.id, 45.0, 48.0, 95.0, 93.0, 'Top 5% percentile candidate in concurrency and clean architecture.'
FROM assessments.assessment_attempts aa
LEFT JOIN assessments.coding_submissions cs ON cs.profile_id = aa.profile_id
LIMIT 6;

INSERT INTO assessments.certificates (assessment_id, profile_id, certificate_number, certificate_url, issued_on, verification_hash)
SELECT a.id, p.id, 'CERT-SKILLOPS-' || a.id || '-' || p.id || '-' || to_char(NOW(), 'YYYYMMDD'), 'https://verify.skillops.io/cert/' || a.id || '/' || p.id, CURRENT_DATE, md5(a.id::text || p.id::text || 'hash')
FROM assessments.assessments a
JOIN user_profiles.profiles p ON p.id = a.id
ON CONFLICT (certificate_number) DO NOTHING;

INSERT INTO assessments.assessment_feedback (evaluation_result_id, strengths, weaknesses, recommendations, generated_at)
SELECT er.id, 'Comprehensive algorithm design, O(1) time complexity, clean documentation', 'Minor edge cases in high-concurrency contention', 'Continue sharpening distributed consensus patterns', NOW()
FROM assessments.evaluation_results er
LIMIT 6;

-- Domain 7: Interviews

INSERT INTO interviews.interviews (application_id, candidate_profile_id, interview_type, mode, scheduled_date, scheduled_time, duration_minutes, status, meeting_link, created_at)
SELECT 1, p.id, 'TECHNICAL', 'VIDEO', CURRENT_DATE + ((p.id % 5)::integer), '14:00:00'::time, 60, 'SCHEDULED', 'https://meet.skillops.io/int-' || p.id, NOW()
FROM user_profiles.profiles p
LIMIT 8;

INSERT INTO interviews.interview_rounds (interview_id, round_name, round_type, round_number, interviewer, duration_minutes, status)
SELECT i.id, 'System Architecture & Concurrency Deep Dive', 'TECHNICAL', 1, 'Sarah Jenkins (Staff Engineer)', 60, 'SCHEDULED'
FROM interviews.interviews i
LIMIT 8;

INSERT INTO interviews.interview_feedback (round_id, reviewer_id, communication_rating, technical_rating, problem_solving_rating, confidence_rating, strengths, improvements, remarks, submitted_at)
SELECT ir.id, 1, 5, 5, 5, 5, 'Exceptional understanding of database locking, distributed caching, and microservices.', 'Keep polishing distributed transactions / SAGA patterns.', 'STRONG_HIRE', NOW()
FROM interviews.interview_rounds ir
LIMIT 6;

INSERT INTO interviews.interview_scorecards (interview_id, communication_score, technical_score, aptitude_score, behavioral_score, coding_score, final_score, recommendation, generated_at)
SELECT i.id, 9.0, 9.5, 9.0, 8.5, 9.5, 9.2, 'STRONG_HIRE', NOW()
FROM interviews.interviews i
WHERE i.id NOT IN (SELECT interview_id FROM interviews.interview_scorecards)
ON CONFLICT (interview_id) DO NOTHING;

INSERT INTO interviews.ai_interview_reports (interview_id, scorecard_id, confidence_score, strengths, weaknesses, improvement_areas, suggested_learning_path, ai_summary, generated_at)
SELECT sc.interview_id, sc.id, 96.0, 'Technical fluency, architectural clarity, modular design', 'Minor hesitation on edge-case concurrency race condition', 'Distributed consensus protocols', 'Advanced Distributed Systems Mastery', 'Candidate showed stellar technical depth and articulated trade-offs with great maturity.', NOW()
FROM interviews.interview_scorecards sc
WHERE sc.interview_id NOT IN (SELECT interview_id FROM interviews.ai_interview_reports WHERE interview_id IS NOT NULL)
LIMIT 6;

-- Domain 8: Recruitment
INSERT INTO recruitment.companies (company_name, industry, website, headquarters, employee_count, company_type, verified, created_at) VALUES ('Stripe Financial Technologies', 'FinTech', 'https://stripe.com', 'San Francisco, CA', 8000, 'ENTERPRISE', true, NOW());
INSERT INTO recruitment.companies (company_name, industry, website, headquarters, employee_count, company_type, verified, created_at) VALUES ('Databricks Big Data & AI', 'Data & AI', 'https://databricks.com', 'San Francisco, CA', 6000, 'ENTERPRISE', true, NOW());
INSERT INTO recruitment.companies (company_name, industry, website, headquarters, employee_count, company_type, verified, created_at) VALUES ('Cloudflare Edge Network', 'Cloud & Cybersecurity', 'https://cloudflare.com', 'Austin, TX', 4000, 'PUBLIC', true, NOW());
INSERT INTO recruitment.companies (company_name, industry, website, headquarters, employee_count, company_type, verified, created_at) VALUES ('Atlassian Enterprise Tools', 'Software Collaboration', 'https://atlassian.com', 'Sydney / Remote', 11000, 'PUBLIC', true, NOW());
INSERT INTO recruitment.companies (company_name, industry, website, headquarters, employee_count, company_type, verified, created_at) VALUES ('Snowflake Cloud Data Platform', 'Cloud Database', 'https://snowflake.com', 'Bozeman, MT', 7000, 'PUBLIC', true, NOW());

INSERT INTO recruitment.jobs (company_id, job_title, department, employment_type, work_mode, location, salary_min, salary_max, experience_level, application_deadline, status)
SELECT c.id, 'Principal Distributed Systems Engineer', 'Core Engineering', 'FULL_TIME', 'HYBRID', 'San Francisco / Remote', 160000.00, 240000.00, 'SENIOR', CURRENT_DATE + INTERVAL '30 days', 'OPEN'
FROM recruitment.companies c
LIMIT 8;

INSERT INTO recruitment.job_requirements (job_id, requirement_type, description, mandatory, priority)
SELECT j.id, 'TECHNICAL', 'Minimum 5+ years building distributed Java / Spring Boot applications in production.', true, 1
FROM recruitment.jobs j
LIMIT 12;

INSERT INTO recruitment.applications (job_id, profile_id, resume_id, applied_on, application_status, ai_match_score, shortlisted)
SELECT j.id, p.id, r.id, CURRENT_DATE - INTERVAL '3 days', 'INTERVIEWING', 94.50, true
FROM recruitment.jobs j
JOIN user_profiles.profiles p ON p.id = j.id
LEFT JOIN user_profiles.resumes r ON r.profile_id = p.id
LIMIT 8;

INSERT INTO recruitment.candidate_matches (application_id, skill_score, project_score, assessment_score, interview_score, overall_score, recommendation, generated_at)
SELECT a.id, 96.0, 92.0, 95.0, 94.0, 94.50, 'TOP_TIER_CANDIDATE', NOW()
FROM recruitment.applications a
ON CONFLICT (application_id) DO NOTHING;

INSERT INTO recruitment.offers (application_id, designation, offered_ctc, joining_date, offer_expiry, offer_status)
SELECT a.id, 'Principal Distributed Systems Engineer', 210000.00, CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE + INTERVAL '14 days', 'OFFERED'
FROM recruitment.applications a
LIMIT 5;

INSERT INTO recruitment.hiring_status (application_id, current_stage, background_verification, documents_verified, onboarding_completed, updated_at)
SELECT a.id, 'OFFER_EXTENDED', true, true, false, NOW()
FROM recruitment.applications a
ON CONFLICT (application_id) DO NOTHING;

-- Domain 9: Notifications

INSERT INTO notifications.notification_preferences (user_id, email_enabled, push_enabled, sms_enabled, whatsapp_enabled, in_app_enabled, marketing_enabled, reminder_enabled, system_alerts_enabled, preferred_language, timezone, updated_at)
SELECT u.id, true, true, false, false, true, false, true, true, 'en_US', 'UTC', NOW()
FROM identity_authentication.users u
WHERE u.id NOT IN (SELECT user_id FROM notifications.notification_preferences)
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO notifications.notifications (user_id, preference_id, title, message, notification_type, delivery_channel, priority, status, is_read, created_at)
SELECT 
    np.user_id,
    np.id,
    'Your Technical Assessment Benchmark Evaluated',
    'Congratulations! You scored in the 95th percentile on the Spring Boot & Architecture challenge.',
    'ASSESSMENT_COMPLETED',
    'IN_APP',
    'HIGH',
    'UNREAD',
    false,
    NOW() - INTERVAL '2 hours'
FROM notifications.notification_preferences np
LIMIT 20;

INSERT INTO notifications.notifications (user_id, preference_id, title, message, notification_type, delivery_channel, priority, status, is_read, created_at)
SELECT 
    np.user_id,
    np.id,
    'New High-Match Job Opening: Principal Engineer',
    'Stripe has posted an opening that matches 94% of your verified skill profile.',
    'JOB_MATCH',
    'IN_APP',
    'NORMAL',
    'UNREAD',
    false,
    NOW() - INTERVAL '1 day'
FROM notifications.notification_preferences np
LIMIT 20;

-- Domain 10: Analytics

INSERT INTO analytics.dashboards (profile_id, dashboard_type, dashboard_name, widgets_count, is_default, last_refreshed, created_at)
SELECT p.id, 'CANDIDATE_360', 'Candidate Executive Career Growth Dashboard', 8, true, NOW(), NOW()
FROM user_profiles.profiles p
LIMIT 12;

INSERT INTO analytics.user_analytics (dashboard_id, profile_id, learning_score, skill_score, assessment_score, project_score, interview_score, employability_score, profile_completion, total_courses, completed_courses, total_projects, completed_projects, certificates_earned, applications_submitted, interviews_completed, offers_received, streak_days, current_rank, recommendation, calculated_at)
SELECT d.id, d.profile_id, 92.0, 94.0, 96.0, 90.0, 95.0, 93.4, 100.0, 6, 4, 5, 4, 3, 8, 3, 2, 24, 'TOP_5_PERCENTILE', 'Ready for Principal Engineer Interviews at Tier 1 Tech Companies', NOW()
FROM analytics.dashboards d
LIMIT 12;

COMMIT;
