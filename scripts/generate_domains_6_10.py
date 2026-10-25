from pathlib import Path

JAVA = Path(r"d:\SkillsOps AI Powered Workforce Development and Hiring Operating System\src\main\java\com\skillops")

TYPE_IMPORTS = {
    "OffsetDateTime": "import java.time.OffsetDateTime;",
    "LocalDate": "import java.time.LocalDate;",
    "LocalTime": "import java.time.LocalTime;",
    "BigDecimal": "import java.math.BigDecimal;",
}


def col_ann(col, nullable=True, unique=False):
    parts = [f'name = "{col}"']
    if not nullable:
        parts.append("nullable = false")
    if unique:
        parts.append("unique = true")
    return "@Column(" + ", ".join(parts) + ")"


def write_entity(package, schema, table, cls, fields):
    extras = set()
    validation_imports = set()
    for col, jtype, *opts in fields:
        if jtype in TYPE_IMPORTS:
            extras.add(TYPE_IMPORTS[jtype])
        nullable = True if not opts else opts[0]
        if not nullable and jtype == "String":
            validation_imports.add("import jakarta.validation.constraints.NotBlank;")
        if not nullable and jtype in ("Long", "Integer"):
            validation_imports.add("import jakarta.validation.constraints.NotNull;")
    extra = "\n".join(sorted(extras | validation_imports))
    if extra:
        extra += "\n"
    lines = [f"""package {package};

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
{extra}
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "{table}", schema = "{schema}")
public class {cls} {{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
"""]
    for col, jtype, *opts in fields:
        nullable = True
        unique = False
        if opts:
            nullable = opts[0]
            if len(opts) > 1:
                unique = opts[1]
        if not nullable and jtype == "String":
            lines.append("    @NotBlank")
        if not nullable and jtype in ("Long", "Integer"):
            lines.append("    @NotNull")
        lines.append(f"    {col_ann(col, nullable, unique)}")
        parts = col.split("_")
        camel = parts[0] + "".join(p.capitalize() for p in parts[1:])
        lines.append(f"    private {jtype} {camel};\n")
    lines.append("}\n")
    path = JAVA / package.replace("com.skillops.", "").replace(".", "/") / f"{cls}.java"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


def write_repo(package, cls):
    text = f"""package {package};

import {package.replace('.repository', '.entity')}.{cls};
import org.springframework.data.jpa.repository.JpaRepository;

public interface {cls}Repository extends JpaRepository<{cls}, Long> {{
}}
"""
    path = JAVA / package.replace("com.skillops.", "").replace(".", "/") / f"{cls}Repository.java"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(text, encoding="utf-8")


def resource_block(path, cls, var):
    return f"""
    @GetMapping("/{path}")
    @Operation(summary = "GET all {cls} records")
    public List<{cls}> list{cls}() {{
        return crud.findAll({var});
    }}

    @GetMapping("/{path}/{{id}}")
    @Operation(summary = "GET {cls} by id")
    public {cls} get{cls}(@PathVariable Long id) {{
        return crud.get({var}, id, "{cls}");
    }}

    @PostMapping("/{path}")
    @Operation(summary = "POST create {cls}")
    public ResponseEntity<{cls}> create{cls}(@Valid @RequestBody {cls} body) {{
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create({var}, body));
    }}

    @PutMapping("/{path}/{{id}}")
    @Operation(summary = "PUT replace {cls}")
    public {cls} replace{cls}(@PathVariable Long id, @Valid @RequestBody {cls} body) {{
        return crud.replace({var}, id, body, "{cls}");
    }}

    @PatchMapping("/{path}/{{id}}")
    @Operation(summary = "PATCH {cls}")
    public {cls} patch{cls}(@PathVariable Long id, @RequestBody Map<String, Object> updates) {{
        return crud.patch({var}, id, updates, "{cls}");
    }}

    @DeleteMapping("/{path}/{{id}}")
    @Operation(summary = "DELETE {cls}")
    public ResponseEntity<Void> delete{cls}(@PathVariable Long id) {{
        crud.delete({var}, id, "{cls}");
        return ResponseEntity.noContent().build();
    }}
"""


def write_controller(folder, tag_const, mapping, class_name, resources):
    pkg = f"com.skillops.{folder}"
    imports = []
    fields = ["    private final CrudFacade crud;"]
    ctor_args = ["CrudFacade crud"]
    assigns = ["        this.crud = crud;"]
    counts = []
    methods = []
    for path, cls, var, repo in resources:
        imports.append(f"import {pkg}.entity.{cls};")
        imports.append(f"import {pkg}.repository.{repo};")
        fields.append(f"    private final {repo} {var};")
        ctor_args.append(f"{repo} {var}")
        assigns.append(f"        this.{var} = {var};")
        counts.append(f'        body.put("{path}", {var}.count());')
        methods.append(resource_block(path, cls, var))
        write_repo(f"{pkg}.repository", cls)
    ctor = ",\n            ".join(ctor_args)
    text = f"""package {pkg}.controller;

import com.skillops.common.CrudFacade;
import com.skillops.config.OpenApiConfig;
{chr(10).join(imports)}
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("{mapping}")
@Tag(name = OpenApiConfig.{tag_const})
public class {class_name} {{

{chr(10).join(fields)}

    public {class_name}(
            {ctor}
    ) {{
{chr(10).join(assigns)}
    }}

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {{
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.{tag_const});
{chr(10).join(counts)}
        return ResponseEntity.ok(body);
    }}
{''.join(methods)}
}}
"""
    path = JAVA / folder / "controller" / f"{class_name}.java"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(text, encoding="utf-8")


domains = {
    "domain6_assessments": {
        "schema": "assessments",
        "tag": "DOMAIN_6",
        "mapping": "/api/v1/assessments-domain",
        "class": "Domain6AssessmentsController",
        "entities": {
            "Assessment": ("assessments", [
                ("title", "String", False),
                ("assessment_type", "String", False),
                ("difficulty", "String"),
                ("total_marks", "Integer", False),
                ("duration_minutes", "Integer", False),
                ("passing_marks", "Integer", False),
                ("is_published", "Boolean", False),
                ("created_at", "OffsetDateTime", False),
            ]),
            "AssessmentQuestion": ("assessment_questions", [
                ("assessment_id", "Long", False),
                ("question_type", "String", False),
                ("question", "String", False),
                ("marks", "Integer", False),
                ("sequence", "Integer", False),
            ]),
            "CodingChallenge": ("coding_challenges", [
                ("assessment_id", "Long", False),
                ("title", "String", False),
                ("language", "String"),
                ("difficulty", "String"),
                ("max_score", "Integer", False),
            ]),
            "AssessmentAttempt": ("assessment_attempts", [
                ("assessment_id", "Long", False),
                ("profile_id", "Long", False),
                ("started_at", "OffsetDateTime", False),
                ("submitted_at", "OffsetDateTime"),
                ("obtained_marks", "Integer"),
                ("passed", "Boolean"),
                ("status", "String", False),
            ]),
            "CodingSubmission": ("coding_submissions", [
                ("challenge_id", "Long", False),
                ("profile_id", "Long", False),
                ("repository_url", "String"),
                ("source_code_url", "String"),
                ("execution_score", "Integer"),
                ("quality_score", "Integer"),
                ("submitted_at", "OffsetDateTime", False),
            ]),
            "EvaluationResult": ("evaluation_results", [
                ("attempt_id", "Long"),
                ("coding_submission_id", "Long"),
                ("objective_score", "Integer"),
                ("coding_score", "Integer"),
                ("ai_score", "Integer"),
                ("final_score", "Integer"),
                ("remarks", "String"),
            ]),
            "AssessmentCertificate": ("certificates", [
                ("assessment_id", "Long", False),
                ("profile_id", "Long", False),
                ("certificate_number", "String", False, True),
                ("certificate_url", "String"),
                ("issued_on", "LocalDate"),
                ("verification_hash", "String"),
            ]),
            "AssessmentFeedback": ("assessment_feedback", [
                ("evaluation_result_id", "Long", False),
                ("strengths", "String"),
                ("weaknesses", "String"),
                ("recommendations", "String"),
                ("generated_at", "OffsetDateTime", False),
            ]),
        },
        "resources": [
            ("assessments", "Assessment", "assessmentRepository", "AssessmentRepository"),
            ("assessment-questions", "AssessmentQuestion", "assessmentQuestionRepository", "AssessmentQuestionRepository"),
            ("coding-challenges", "CodingChallenge", "codingChallengeRepository", "CodingChallengeRepository"),
            ("assessment-attempts", "AssessmentAttempt", "assessmentAttemptRepository", "AssessmentAttemptRepository"),
            ("coding-submissions", "CodingSubmission", "codingSubmissionRepository", "CodingSubmissionRepository"),
            ("evaluation-results", "EvaluationResult", "evaluationResultRepository", "EvaluationResultRepository"),
            ("certificates", "AssessmentCertificate", "assessmentCertificateRepository", "AssessmentCertificateRepository"),
            ("assessment-feedback", "AssessmentFeedback", "assessmentFeedbackRepository", "AssessmentFeedbackRepository"),
        ],
    },
    "domain7_interviews": {
        "schema": "interviews",
        "tag": "DOMAIN_7",
        "mapping": "/api/v1/interviews-domain",
        "class": "Domain7InterviewsController",
        "entities": {
            "Interview": ("interviews", [
                ("application_id", "Long"),
                ("candidate_profile_id", "Long", False),
                ("interview_type", "String"),
                ("mode", "String"),
                ("scheduled_date", "LocalDate"),
                ("scheduled_time", "LocalTime"),
                ("duration_minutes", "Integer"),
                ("status", "String", False),
                ("meeting_link", "String"),
                ("created_at", "OffsetDateTime", False),
            ]),
            "InterviewRound": ("interview_rounds", [
                ("interview_id", "Long", False),
                ("round_name", "String", False),
                ("round_type", "String"),
                ("round_number", "Integer", False),
                ("interviewer", "String"),
                ("duration_minutes", "Integer"),
                ("status", "String", False),
            ]),
            "InterviewFeedback": ("interview_feedback", [
                ("round_id", "Long", False),
                ("reviewer_id", "Long"),
                ("communication_rating", "Integer"),
                ("technical_rating", "Integer"),
                ("problem_solving_rating", "Integer"),
                ("confidence_rating", "Integer"),
                ("strengths", "String"),
                ("improvements", "String"),
                ("remarks", "String"),
                ("submitted_at", "OffsetDateTime", False),
            ]),
            "InterviewScorecard": ("interview_scorecards", [
                ("interview_id", "Long", False, True),
                ("communication_score", "Integer"),
                ("technical_score", "Integer"),
                ("aptitude_score", "Integer"),
                ("behavioral_score", "Integer"),
                ("coding_score", "Integer"),
                ("final_score", "Integer"),
                ("recommendation", "String"),
                ("generated_at", "OffsetDateTime", False),
            ]),
            "AiInterviewReport": ("ai_interview_reports", [
                ("interview_id", "Long", False),
                ("scorecard_id", "Long"),
                ("confidence_score", "BigDecimal"),
                ("strengths", "String"),
                ("weaknesses", "String"),
                ("improvement_areas", "String"),
                ("suggested_learning_path", "String"),
                ("ai_summary", "String"),
                ("generated_at", "OffsetDateTime", False),
            ]),
        },
        "resources": [
            ("interviews", "Interview", "interviewRepository", "InterviewRepository"),
            ("interview-rounds", "InterviewRound", "interviewRoundRepository", "InterviewRoundRepository"),
            ("interview-feedback", "InterviewFeedback", "interviewFeedbackRepository", "InterviewFeedbackRepository"),
            ("interview-scorecards", "InterviewScorecard", "interviewScorecardRepository", "InterviewScorecardRepository"),
            ("ai-interview-reports", "AiInterviewReport", "aiInterviewReportRepository", "AiInterviewReportRepository"),
        ],
    },
    "domain8_recruitment": {
        "schema": "recruitment",
        "tag": "DOMAIN_8",
        "mapping": "/api/v1/recruitment-domain",
        "class": "Domain8RecruitmentController",
        "entities": {
            "Company": ("companies", [
                ("company_name", "String", False),
                ("industry", "String"),
                ("website", "String"),
                ("headquarters", "String"),
                ("employee_count", "Integer"),
                ("company_type", "String"),
                ("verified", "Boolean", False),
                ("created_at", "OffsetDateTime", False),
            ]),
            "Job": ("jobs", [
                ("company_id", "Long", False),
                ("job_title", "String", False),
                ("department", "String"),
                ("employment_type", "String"),
                ("work_mode", "String"),
                ("location", "String"),
                ("salary_min", "BigDecimal"),
                ("salary_max", "BigDecimal"),
                ("experience_level", "String"),
                ("application_deadline", "LocalDate"),
                ("status", "String", False),
            ]),
            "JobRequirement": ("job_requirements", [
                ("job_id", "Long", False),
                ("requirement_type", "String", False),
                ("description", "String", False),
                ("mandatory", "Boolean", False),
                ("priority", "Integer"),
            ]),
            "JobApplication": ("applications", [
                ("job_id", "Long", False),
                ("profile_id", "Long", False),
                ("resume_id", "Long"),
                ("applied_on", "LocalDate", False),
                ("application_status", "String", False),
                ("ai_match_score", "Integer"),
                ("shortlisted", "Boolean", False),
            ]),
            "CandidateMatch": ("candidate_matches", [
                ("application_id", "Long", False, True),
                ("skill_score", "Integer"),
                ("project_score", "Integer"),
                ("assessment_score", "Integer"),
                ("interview_score", "Integer"),
                ("overall_score", "Integer"),
                ("recommendation", "String"),
                ("generated_at", "OffsetDateTime", False),
            ]),
            "Offer": ("offers", [
                ("application_id", "Long", False),
                ("designation", "String", False),
                ("offered_ctc", "BigDecimal"),
                ("joining_date", "LocalDate"),
                ("offer_expiry", "LocalDate"),
                ("offer_status", "String", False),
            ]),
            "HiringStatus": ("hiring_status", [
                ("application_id", "Long", False, True),
                ("current_stage", "String", False),
                ("background_verification", "Boolean", False),
                ("documents_verified", "Boolean", False),
                ("onboarding_completed", "Boolean", False),
                ("updated_at", "OffsetDateTime", False),
            ]),
        },
        "resources": [
            ("companies", "Company", "companyRepository", "CompanyRepository"),
            ("jobs", "Job", "jobRepository", "JobRepository"),
            ("job-requirements", "JobRequirement", "jobRequirementRepository", "JobRequirementRepository"),
            ("applications", "JobApplication", "jobApplicationRepository", "JobApplicationRepository"),
            ("candidate-matches", "CandidateMatch", "candidateMatchRepository", "CandidateMatchRepository"),
            ("offers", "Offer", "offerRepository", "OfferRepository"),
            ("hiring-status", "HiringStatus", "hiringStatusRepository", "HiringStatusRepository"),
        ],
    },
    "domain9_notifications": {
        "schema": "notifications",
        "tag": "DOMAIN_9",
        "mapping": "/api/v1/notifications-domain",
        "class": "Domain9NotificationsController",
        "entities": {
            "NotificationPreference": ("notification_preferences", [
                ("user_id", "Long", False, True),
                ("email_enabled", "Boolean", False),
                ("push_enabled", "Boolean", False),
                ("sms_enabled", "Boolean", False),
                ("whatsapp_enabled", "Boolean", False),
                ("in_app_enabled", "Boolean", False),
                ("marketing_enabled", "Boolean", False),
                ("reminder_enabled", "Boolean", False),
                ("system_alerts_enabled", "Boolean", False),
                ("preferred_language", "String"),
                ("timezone", "String"),
                ("updated_at", "OffsetDateTime", False),
            ]),
            "AppNotification": ("notifications", [
                ("user_id", "Long", False),
                ("preference_id", "Long"),
                ("title", "String", False),
                ("message", "String", False),
                ("notification_type", "String"),
                ("delivery_channel", "String"),
                ("priority", "String"),
                ("status", "String", False),
                ("reference_module", "String"),
                ("reference_id", "Long"),
                ("is_read", "Boolean", False),
                ("scheduled_at", "OffsetDateTime"),
                ("sent_at", "OffsetDateTime"),
                ("created_at", "OffsetDateTime", False),
            ]),
        },
        "resources": [
            ("notification-preferences", "NotificationPreference", "notificationPreferenceRepository", "NotificationPreferenceRepository"),
            ("notifications", "AppNotification", "appNotificationRepository", "AppNotificationRepository"),
        ],
    },
    "domain10_analytics": {
        "schema": "analytics",
        "tag": "DOMAIN_10",
        "mapping": "/api/v1/analytics-domain",
        "class": "Domain10AnalyticsController",
        "entities": {
            "Dashboard": ("dashboards", [
                ("profile_id", "Long", False),
                ("dashboard_type", "String", False),
                ("dashboard_name", "String", False),
                ("widgets_count", "Integer", False),
                ("is_default", "Boolean", False),
                ("last_refreshed", "OffsetDateTime"),
                ("created_at", "OffsetDateTime", False),
            ]),
            "UserAnalytics": ("user_analytics", [
                ("dashboard_id", "Long", False),
                ("profile_id", "Long", False),
                ("learning_score", "Integer"),
                ("skill_score", "Integer"),
                ("assessment_score", "Integer"),
                ("project_score", "Integer"),
                ("interview_score", "Integer"),
                ("employability_score", "Integer"),
                ("profile_completion", "Integer"),
                ("total_courses", "Integer"),
                ("completed_courses", "Integer"),
                ("total_projects", "Integer"),
                ("completed_projects", "Integer"),
                ("certificates_earned", "Integer"),
                ("applications_submitted", "Integer"),
                ("interviews_completed", "Integer"),
                ("offers_received", "Integer"),
                ("streak_days", "Integer"),
                ("current_rank", "String"),
                ("recommendation", "String"),
                ("calculated_at", "OffsetDateTime", False),
            ]),
        },
        "resources": [
            ("dashboards", "Dashboard", "dashboardRepository", "DashboardRepository"),
            ("user-analytics", "UserAnalytics", "userAnalyticsRepository", "UserAnalyticsRepository"),
        ],
    },
}

for folder, meta in domains.items():
    pkg_entity = f"com.skillops.{folder}.entity"
    for cls, (table, fields) in meta["entities"].items():
        write_entity(pkg_entity, meta["schema"], table, cls, fields)
    write_controller(folder, meta["tag"], meta["mapping"], meta["class"], meta["resources"])
    print("generated", folder)

print("done")
