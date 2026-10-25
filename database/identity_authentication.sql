-- SkillOps Identity & Authentication schema and seed data
-- Sample user password for all seeded accounts: password
-- Hash: Spring BCrypt of "password"

CREATE TABLE IF NOT EXISTS roles (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(80) NOT NULL UNIQUE,
    description VARCHAR(500),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS permissions (
    id          BIGSERIAL PRIMARY KEY,
    module      VARCHAR(80) NOT NULL,
    action      VARCHAR(80) NOT NULL,
    description VARCHAR(500),
    CONSTRAINT uq_permissions_module_action UNIQUE (module, action)
);

CREATE TABLE IF NOT EXISTS users (
    id             BIGSERIAL PRIMARY KEY,
    tenant_id      BIGINT,
    email          VARCHAR(255) NOT NULL UNIQUE,
    password_hash  VARCHAR(255) NOT NULL,
    phone          VARCHAR(20),
    status         VARCHAR(30) NOT NULL,
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    last_login     TIMESTAMPTZ,
    created_at     TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS user_roles (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    role_id     BIGINT NOT NULL REFERENCES roles (id) ON DELETE RESTRICT,
    assigned_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_user_roles_user_role UNIQUE (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS sessions (
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    access_token  VARCHAR(1000) NOT NULL,
    refresh_token VARCHAR(1000) NOT NULL,
    ip_address    VARCHAR(45),
    device        VARCHAR(255),
    expires_at    TIMESTAMPTZ NOT NULL
);

CREATE TABLE IF NOT EXISTS refresh_tokens (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    token       VARCHAR(500) NOT NULL UNIQUE,
    expiry_date TIMESTAMPTZ NOT NULL
);

CREATE TABLE IF NOT EXISTS login_history (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    login_time  TIMESTAMPTZ NOT NULL,
    logout_time TIMESTAMPTZ,
    device      VARCHAR(255),
    browser     VARCHAR(120),
    ip_address  VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS audit_logs (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT REFERENCES users (id) ON DELETE SET NULL,
    action      VARCHAR(80) NOT NULL,
    entity_name VARCHAR(80) NOT NULL,
    entity_id   BIGINT,
    old_value   TEXT,
    new_value   TEXT,
    timestamp   TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_users_tenant_id ON users (tenant_id);
CREATE INDEX IF NOT EXISTS idx_users_status ON users (status);
CREATE INDEX IF NOT EXISTS idx_user_roles_user_id ON user_roles (user_id);
CREATE INDEX IF NOT EXISTS idx_sessions_user_id ON sessions (user_id);
CREATE INDEX IF NOT EXISTS idx_refresh_tokens_user_id ON refresh_tokens (user_id);
CREATE INDEX IF NOT EXISTS idx_login_history_user_id ON login_history (user_id);
CREATE INDEX IF NOT EXISTS idx_audit_logs_user_id ON audit_logs (user_id);

INSERT INTO roles (name, description, created_at) VALUES
('SUPER_ADMIN', 'Full access. Manage all modules, users, settings, and billing.', NOW() - INTERVAL '90 days'),
('ADMIN', 'System access. User management and module management.', NOW() - INTERVAL '90 days'),
('INSTITUTION_ADMIN', 'Institution access. Manage students, courses, and placements.', NOW() - INTERVAL '80 days'),
('EMPLOYER', 'Company access. Post jobs, view applicants, and analytics.', NOW() - INTERVAL '80 days'),
('RECRUITER', 'Hiring access. View candidates and schedule interviews.', NOW() - INTERVAL '70 days'),
('MENTOR_EVALUATOR', 'Evaluation access. Assess learners and provide feedback.', NOW() - INTERVAL '70 days'),
('STUDENT_USER', 'Personal access. Learn, assess, apply, and track progress.', NOW() - INTERVAL '60 days');

INSERT INTO permissions (module, action, description) VALUES
('IDENTITY', 'CREATE', 'Create users, roles, and identity records'),
('IDENTITY', 'READ', 'View users, roles, and identity records'),
('IDENTITY', 'UPDATE', 'Update users, roles, and identity records'),
('IDENTITY', 'DELETE', 'Delete users, roles, and identity records'),
('LEARNING', 'MANAGE', 'Create and manage courses, learning paths, and content'),
('LEARNING', 'ENROLL', 'Enroll in courses and track learning progress'),
('ASSESSMENT', 'CREATE', 'Create assessments and certification exams'),
('ASSESSMENT', 'ATTEMPT', 'Attempt assessments and mock interviews'),
('ASSESSMENT', 'EVALUATE', 'Evaluate submissions and issue feedback'),
('RECRUITMENT', 'POST_JOB', 'Post and manage job openings'),
('RECRUITMENT', 'APPLY', 'Apply to jobs and track applications'),
('RECRUITMENT', 'REVIEW', 'Review candidates and schedule interviews'),
('ANALYTICS', 'VIEW', 'View dashboards and workforce analytics'),
('SETTINGS', 'MANAGE', 'Manage platform settings and billing'),
('PASSPORT', 'READ', 'View digital skill passport records'),
('PASSPORT', 'UPDATE', 'Update digital skill passport records');

INSERT INTO users (tenant_id, email, password_hash, phone, status, email_verified, last_login, created_at, updated_at) VALUES
(1, 'superadmin@skillops.io', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000001', 'ACTIVE', TRUE, NOW() - INTERVAL '2 hours', NOW() - INTERVAL '90 days', NOW() - INTERVAL '2 hours'),
(1, 'admin@skillops.io', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000002', 'ACTIVE', TRUE, NOW() - INTERVAL '1 day', NOW() - INTERVAL '85 days', NOW() - INTERVAL '1 day'),
(1, 'ops.admin@skillops.io', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000003', 'ACTIVE', TRUE, NOW() - INTERVAL '3 days', NOW() - INTERVAL '80 days', NOW() - INTERVAL '3 days'),
(2, 'dean@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000004', 'ACTIVE', TRUE, NOW() - INTERVAL '5 hours', NOW() - INTERVAL '70 days', NOW() - INTERVAL '5 hours'),
(2, 'placement@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000005', 'ACTIVE', TRUE, NOW() - INTERVAL '8 hours', NOW() - INTERVAL '65 days', NOW() - INTERVAL '8 hours'),
(3, 'talent@novatech.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000006', 'ACTIVE', TRUE, NOW() - INTERVAL '12 hours', NOW() - INTERVAL '60 days', NOW() - INTERVAL '12 hours'),
(3, 'hr@novatech.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000007', 'ACTIVE', TRUE, NOW() - INTERVAL '2 days', NOW() - INTERVAL '55 days', NOW() - INTERVAL '2 days'),
(3, 'recruiter.anita@novatech.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000008', 'ACTIVE', TRUE, NOW() - INTERVAL '6 hours', NOW() - INTERVAL '50 days', NOW() - INTERVAL '6 hours'),
(3, 'recruiter.raj@novatech.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000009', 'INACTIVE', TRUE, NOW() - INTERVAL '20 days', NOW() - INTERVAL '48 days', NOW() - INTERVAL '20 days'),
(2, 'mentor.kavya@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000010', 'ACTIVE', TRUE, NOW() - INTERVAL '4 hours', NOW() - INTERVAL '45 days', NOW() - INTERVAL '4 hours'),
(2, 'evaluator.arjun@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000011', 'ACTIVE', TRUE, NOW() - INTERVAL '9 hours', NOW() - INTERVAL '40 days', NOW() - INTERVAL '9 hours'),
(2, 'priya.student@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000012', 'ACTIVE', TRUE, NOW() - INTERVAL '30 minutes', NOW() - INTERVAL '35 days', NOW() - INTERVAL '30 minutes'),
(2, 'rohan.student@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000013', 'ACTIVE', TRUE, NOW() - INTERVAL '1 day', NOW() - INTERVAL '30 days', NOW() - INTERVAL '1 day'),
(2, 'meera.student@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000014', 'PENDING', FALSE, NULL, NOW() - INTERVAL '5 days', NOW() - INTERVAL '5 days'),
(2, 'vikram.student@apex-university.edu', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '+919800000015', 'SUSPENDED', TRUE, NOW() - INTERVAL '15 days', NOW() - INTERVAL '25 days', NOW() - INTERVAL '10 days');

INSERT INTO user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW() - INTERVAL '80 days'
FROM users u JOIN roles r ON r.name = 'SUPER_ADMIN'
WHERE u.email = 'superadmin@skillops.io';

INSERT INTO user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW() - INTERVAL '75 days'
FROM users u JOIN roles r ON r.name = 'ADMIN'
WHERE u.email IN ('admin@skillops.io', 'ops.admin@skillops.io');

INSERT INTO user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW() - INTERVAL '65 days'
FROM users u JOIN roles r ON r.name = 'INSTITUTION_ADMIN'
WHERE u.email IN ('dean@apex-university.edu', 'placement@apex-university.edu');

INSERT INTO user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW() - INTERVAL '55 days'
FROM users u JOIN roles r ON r.name = 'EMPLOYER'
WHERE u.email IN ('talent@novatech.com', 'hr@novatech.com');

INSERT INTO user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW() - INTERVAL '48 days'
FROM users u JOIN roles r ON r.name = 'RECRUITER'
WHERE u.email IN ('recruiter.anita@novatech.com', 'recruiter.raj@novatech.com', 'hr@novatech.com');

INSERT INTO user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW() - INTERVAL '40 days'
FROM users u JOIN roles r ON r.name = 'MENTOR_EVALUATOR'
WHERE u.email IN ('mentor.kavya@apex-university.edu', 'evaluator.arjun@apex-university.edu');

INSERT INTO user_roles (user_id, role_id, assigned_at)
SELECT u.id, r.id, NOW() - INTERVAL '30 days'
FROM users u JOIN roles r ON r.name = 'STUDENT_USER'
WHERE u.email IN (
    'priya.student@apex-university.edu',
    'rohan.student@apex-university.edu',
    'meera.student@apex-university.edu',
    'vikram.student@apex-university.edu'
);

INSERT INTO sessions (user_id, access_token, refresh_token, ip_address, device, expires_at)
SELECT id, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.superadmin.access', 'rt_superadmin_001', '10.0.0.11', 'Windows 11 / Chrome', NOW() + INTERVAL '8 hours'
FROM users WHERE email = 'superadmin@skillops.io';

INSERT INTO sessions (user_id, access_token, refresh_token, ip_address, device, expires_at)
SELECT id, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.admin.access', 'rt_admin_001', '10.0.0.12', 'macOS / Safari', NOW() + INTERVAL '6 hours'
FROM users WHERE email = 'admin@skillops.io';

INSERT INTO sessions (user_id, access_token, refresh_token, ip_address, device, expires_at)
SELECT id, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.dean.access', 'rt_dean_001', '103.21.44.10', 'Windows 11 / Edge', NOW() + INTERVAL '4 hours'
FROM users WHERE email = 'dean@apex-university.edu';

INSERT INTO sessions (user_id, access_token, refresh_token, ip_address, device, expires_at)
SELECT id, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.anita.access', 'rt_anita_001', '49.36.12.88', 'Android / Chrome', NOW() + INTERVAL '3 hours'
FROM users WHERE email = 'recruiter.anita@novatech.com';

INSERT INTO sessions (user_id, access_token, refresh_token, ip_address, device, expires_at)
SELECT id, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.priya.access', 'rt_priya_001', '49.36.18.21', 'iPhone / Safari', NOW() + INTERVAL '2 hours'
FROM users WHERE email = 'priya.student@apex-university.edu';

INSERT INTO sessions (user_id, access_token, refresh_token, ip_address, device, expires_at)
SELECT id, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.kavya.access', 'rt_kavya_001', '103.21.44.22', 'macOS / Chrome', NOW() + INTERVAL '5 hours'
FROM users WHERE email = 'mentor.kavya@apex-university.edu';

INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_superadmin_001', NOW() + INTERVAL '7 days' FROM users WHERE email = 'superadmin@skillops.io';
INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_admin_001', NOW() + INTERVAL '7 days' FROM users WHERE email = 'admin@skillops.io';
INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_dean_001', NOW() + INTERVAL '7 days' FROM users WHERE email = 'dean@apex-university.edu';
INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_anita_001', NOW() + INTERVAL '7 days' FROM users WHERE email = 'recruiter.anita@novatech.com';
INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_priya_001', NOW() + INTERVAL '7 days' FROM users WHERE email = 'priya.student@apex-university.edu';
INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_kavya_001', NOW() + INTERVAL '7 days' FROM users WHERE email = 'mentor.kavya@apex-university.edu';
INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_rohan_expired', NOW() - INTERVAL '1 day' FROM users WHERE email = 'rohan.student@apex-university.edu';
INSERT INTO refresh_tokens (user_id, token, expiry_date)
SELECT id, 'rt_talent_001', NOW() + INTERVAL '14 days' FROM users WHERE email = 'talent@novatech.com';

INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '2 hours', NULL, 'Dell XPS 15', 'Chrome 128', '10.0.0.11'
FROM users WHERE email = 'superadmin@skillops.io';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '2 days', NOW() - INTERVAL '1 day 20 hours', 'Dell XPS 15', 'Chrome 128', '10.0.0.11'
FROM users WHERE email = 'superadmin@skillops.io';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '1 day', NOW() - INTERVAL '20 hours', 'MacBook Pro', 'Safari 18', '10.0.0.12'
FROM users WHERE email = 'admin@skillops.io';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '5 hours', NULL, 'Lenovo ThinkPad', 'Edge 127', '103.21.44.10'
FROM users WHERE email = 'dean@apex-university.edu';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '12 hours', NOW() - INTERVAL '10 hours', 'HP EliteBook', 'Chrome 127', '49.36.12.10'
FROM users WHERE email = 'talent@novatech.com';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '6 hours', NULL, 'Pixel 8', 'Chrome Mobile', '49.36.12.88'
FROM users WHERE email = 'recruiter.anita@novatech.com';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '4 hours', NULL, 'MacBook Air', 'Chrome 128', '103.21.44.22'
FROM users WHERE email = 'mentor.kavya@apex-university.edu';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '30 minutes', NULL, 'iPhone 15', 'Safari Mobile', '49.36.18.21'
FROM users WHERE email = 'priya.student@apex-university.edu';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '1 day', NOW() - INTERVAL '23 hours', 'Windows Desktop', 'Firefox 129', '49.36.18.44'
FROM users WHERE email = 'rohan.student@apex-university.edu';
INSERT INTO login_history (user_id, login_time, logout_time, device, browser, ip_address)
SELECT id, NOW() - INTERVAL '15 days', NOW() - INTERVAL '15 days' + INTERVAL '40 minutes', 'Android Phone', 'Chrome Mobile', '49.36.18.90'
FROM users WHERE email = 'vikram.student@apex-university.edu';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'CREATE', 'User', 12, NULL, '{"email":"priya.student@apex-university.edu","status":"ACTIVE"}', NOW() - INTERVAL '35 days'
FROM users WHERE email = 'admin@skillops.io';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'UPDATE', 'User', 15, '{"status":"ACTIVE"}', '{"status":"SUSPENDED"}', NOW() - INTERVAL '10 days'
FROM users WHERE email = 'dean@apex-university.edu';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'ASSIGN_ROLE', 'UserRole', 1, NULL, '{"role":"SUPER_ADMIN"}', NOW() - INTERVAL '80 days'
FROM users WHERE email = 'superadmin@skillops.io';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'LOGIN', 'Session', 1, NULL, '{"ip":"10.0.0.11","device":"Windows 11 / Chrome"}', NOW() - INTERVAL '2 hours'
FROM users WHERE email = 'superadmin@skillops.io';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'UPDATE', 'User', 14, '{"email_verified":false}', '{"email_verified":false,"status":"PENDING"}', NOW() - INTERVAL '5 days'
FROM users WHERE email = 'ops.admin@skillops.io';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'CREATE', 'Session', 5, NULL, '{"user":"priya.student@apex-university.edu"}', NOW() - INTERVAL '30 minutes'
FROM users WHERE email = 'priya.student@apex-university.edu';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'UPDATE', 'Role', 5, '{"description":"Hiring access"}', '{"description":"Hiring access. View candidates and schedule interviews."}', NOW() - INTERVAL '20 days'
FROM users WHERE email = 'admin@skillops.io';

INSERT INTO audit_logs (user_id, action, entity_name, entity_id, old_value, new_value, timestamp)
SELECT id, 'LOGIN', 'Session', 4, NULL, '{"ip":"49.36.12.88"}', NOW() - INTERVAL '6 hours'
FROM users WHERE email = 'recruiter.anita@novatech.com';
