from pathlib import Path

ROOT = Path(r"d:\SkillsOps AI Powered Workforce Development and Hiring Operating System")
JAVA = ROOT / "src" / "main" / "java" / "com" / "skillops"
OLD = {
    "domain": JAVA / "domain",
    "repository": JAVA / "repository",
    "dto": JAVA / "dto",
    "controller": JAVA / "controller",
}
NEW_ID = JAVA / "domain1_identity_authentication"

ENTITY_HEADER = """package {pkg};

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
{extra_imports}

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "{table}", schema = "{schema}")
public class {cls} {{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
"""

TYPE_IMPORTS = {
    "OffsetDateTime": "import java.time.OffsetDateTime;",
    "LocalDate": "import java.time.LocalDate;",
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
    for _, jtype, *_ in fields:
        if jtype in TYPE_IMPORTS:
            extras.add(TYPE_IMPORTS[jtype])
    extra = "\n".join(sorted(extras))
    if extra:
        extra = extra + "\n"
    body = [ENTITY_HEADER.format(pkg=package, extra_imports=extra, table=table, schema=schema, cls=cls)]
    for col, jtype, *opts in fields:
        nullable = True
        unique = False
        if opts:
            nullable = opts[0]
            if len(opts) > 1:
                unique = opts[1]
        body.append(f"    {col_ann(col, nullable, unique)}")
        parts = col.split("_")
        camel = parts[0] + "".join(p.capitalize() for p in parts[1:])
        body.append(f"    private {jtype} {camel};\n")
    body.append("}\n")
    path = JAVA / package.replace("com.skillops.", "").replace(".", "/") / f"{cls}.java"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(body).replace("\n\n\n", "\n\n"), encoding="utf-8")


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


def write_controller(domain_pkg, tag, mapping, entities):
    imports = []
    fields = []
    ctor_args = []
    assigns = []
    endpoints = []
    counts = []
    for cls in entities:
        repo = f"{cls}Repository"
        var = cls[0].lower() + cls[1:] + "Repository"
        imports.append(f"import {domain_pkg}.entity.{cls};")
        imports.append(f"import {domain_pkg}.repository.{repo};")
        fields.append(f"    private final {repo} {var};")
        ctor_args.append(f"{repo} {var}")
        assigns.append(f"        this.{var} = {var};")
        path = "".join(["-" + c.lower() if c.isupper() else c for c in cls]).lstrip("-")
        if path.endswith("y") and not path.endswith("ay") and not path.endswith("ey"):
            list_path = path[:-1] + "ies"
        elif path.endswith("s"):
            list_path = path + "es"
        else:
            list_path = path + "s"
        endpoints.append(f"""
    @GetMapping("/{list_path}")
    @Operation(summary = "List {cls} records")
    public List<{cls}> list{cls}() {{
        return {var}.findAll();
    }}""")
        counts.append(f'        body.put("{list_path}", {var}.count());')

    ctor_sig = ",\n            ".join(ctor_args)
    class_name = tag.replace(" & ", "").replace(" ", "") + "Controller"
    text = f"""package {domain_pkg}.controller;

{chr(10).join(imports)}
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("{mapping}")
@Tag(name = "{tag}")
public class {class_name} {{

{chr(10).join(fields)}

    public {class_name}(
            {ctor_sig}
    ) {{
{chr(10).join(assigns)}
    }}

    @GetMapping("/summary")
    @Operation(summary = "Row counts for this domain")
    public ResponseEntity<Map<String, Object>> summary() {{
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", "{tag}");
{chr(10).join(counts)}
        return ResponseEntity.ok(body);
    }}
{chr(10).join(endpoints)}
}}
"""
    path = JAVA / domain_pkg.replace("com.skillops.", "").replace(".", "/") / "controller" / f"{class_name}.java"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(text, encoding="utf-8")


mapping = [
    (OLD["domain"], NEW_ID / "entity", "com.skillops.domain", "com.skillops.domain1_identity_authentication.entity", True),
    (OLD["repository"], NEW_ID / "repository", "com.skillops.repository", "com.skillops.domain1_identity_authentication.repository", False),
    (OLD["dto"], NEW_ID / "dto", "com.skillops.dto", "com.skillops.domain1_identity_authentication.dto", False),
    (OLD["controller"], NEW_ID / "controller", "com.skillops.controller", "com.skillops.domain1_identity_authentication.controller", False),
]
for src, dest, old_pkg, new_pkg, add_schema in mapping:
    dest.mkdir(parents=True, exist_ok=True)
    if not src.exists():
        continue
    for f in src.glob("*.java"):
        text = f.read_text(encoding="utf-8")
        text = text.replace(old_pkg, new_pkg)
        text = text.replace("com.skillops.domain.", "com.skillops.domain1_identity_authentication.entity.")
        text = text.replace("com.skillops.repository.", "com.skillops.domain1_identity_authentication.repository.")
        text = text.replace("com.skillops.dto.", "com.skillops.domain1_identity_authentication.dto.")
        if add_schema:
            text = text.replace('@Table(name = "', '@Table(schema = "identity_authentication", name = "')
        (dest / f.name).write_text(text, encoding="utf-8")
        f.unlink()
    try:
        src.rmdir()
    except OSError:
        pass

domains = {
    "domain2_user_profiles": {
        "schema": "user_profiles",
        "tag": "Domain 2 User Profiles",
        "mapping": "/api/v1/profiles-domain",
        "entities": {
            "Profile": ("profiles", [
                ("user_id", "Long", False, True),
                ("first_name", "String", False),
                ("last_name", "String", False),
                ("date_of_birth", "LocalDate"),
                ("gender", "String"),
                ("bio", "String"),
                ("profile_picture", "String"),
                ("city", "String"),
                ("state", "String"),
                ("country", "String"),
                ("linkedin_url", "String"),
                ("github_url", "String"),
                ("created_at", "OffsetDateTime", False),
                ("updated_at", "OffsetDateTime", False),
            ]),
            "Resume": ("resumes", [
                ("profile_id", "Long", False),
                ("file_name", "String", False),
                ("file_url", "String", False),
                ("file_type", "String"),
                ("parsed_text", "String"),
                ("version", "Integer", False),
                ("is_primary", "Boolean", False),
                ("uploaded_at", "OffsetDateTime", False),
            ]),
            "Education": ("educations", [
                ("profile_id", "Long", False),
                ("institute_name", "String", False),
                ("degree", "String", False),
                ("specialization", "String"),
                ("cgpa", "BigDecimal"),
                ("passing_year", "Integer"),
                ("start_year", "Integer"),
                ("end_year", "Integer"),
                ("is_current", "Boolean", False),
            ]),
            "Experience": ("experiences", [
                ("profile_id", "Long", False),
                ("company_name", "String", False),
                ("designation", "String", False),
                ("employment_type", "String"),
                ("start_date", "LocalDate", False),
                ("end_date", "LocalDate"),
                ("currently_working", "Boolean", False),
                ("location", "String"),
                ("responsibilities", "String"),
            ]),
            "ProfileCertification": ("certifications", [
                ("profile_id", "Long", False),
                ("certification_name", "String", False),
                ("issuer", "String", False),
                ("credential_id", "String"),
                ("credential_url", "String"),
                ("issue_date", "LocalDate"),
                ("expiry_date", "LocalDate"),
            ]),
            "CareerGoal": ("career_goals", [
                ("profile_id", "Long", False),
                ("target_role", "String", False),
                ("target_industry", "String"),
                ("target_company", "String"),
                ("preferred_location", "String"),
                ("expected_ctc", "BigDecimal"),
                ("employment_type", "String"),
                ("target_timeline_months", "Integer"),
                ("created_at", "OffsetDateTime", False),
            ]),
            "SkillPassport": ("skill_passports", [
                ("profile_id", "Long", False, True),
                ("passport_score", "BigDecimal", False),
                ("total_skills", "Integer", False),
                ("verified_skills", "Integer", False),
                ("completed_courses", "Integer", False),
                ("completed_projects", "Integer", False),
                ("certificates_earned", "Integer", False),
                ("verification_status", "String", False),
                ("last_updated", "OffsetDateTime", False),
            ]),
        },
    },
    "domain3_skills": {
        "schema": "skills",
        "tag": "Domain 3 Skills",
        "mapping": "/api/v1/skills-domain",
        "entities": {
            "SkillCategory": ("skill_categories", [
                ("category_name", "String", False, True),
                ("description", "String"),
                ("icon", "String"),
                ("is_active", "Boolean", False),
                ("created_at", "OffsetDateTime", False),
            ]),
            "Skill": ("skills", [
                ("category_id", "Long", False),
                ("skill_name", "String", False),
                ("description", "String"),
                ("difficulty_level", "String"),
                ("is_verified", "Boolean", False),
                ("created_at", "OffsetDateTime", False),
            ]),
            "SkillAssessment": ("skill_assessments", [
                ("skill_id", "Long", False),
                ("assessment_type", "String", False),
                ("passing_score", "Integer", False),
                ("total_marks", "Integer", False),
                ("duration_minutes", "Integer", False),
            ]),
            "Competency": ("competencies", [
                ("skill_id", "Long", False),
                ("competency_name", "String", False),
                ("description", "String"),
                ("display_order", "Integer", False),
            ]),
            "CompetencyLevel": ("competency_levels", [
                ("competency_id", "Long", False),
                ("level_name", "String", False),
                ("level_value", "Integer", False),
                ("criteria", "String"),
            ]),
            "UserSkill": ("user_skills", [
                ("profile_id", "Long", False),
                ("skill_id", "Long", False),
                ("competency_level_id", "Long"),
                ("skill_assessment_id", "Long"),
                ("proficiency_score", "Integer", False),
                ("verified", "Boolean", False),
                ("last_used", "LocalDate"),
                ("updated_at", "OffsetDateTime", False),
            ]),
            "SkillGap": ("skill_gaps", [
                ("profile_id", "Long", False),
                ("skill_id", "Long", False),
                ("current_level", "Integer", False),
                ("target_level", "Integer", False),
                ("gap_score", "Integer", False),
                ("priority", "String"),
            ]),
            "SkillRecommendation": ("skill_recommendations", [
                ("profile_id", "Long", False),
                ("skill_id", "Long", False),
                ("recommendation_reason", "String"),
                ("recommendation_score", "Integer"),
                ("recommendation_type", "String"),
                ("generated_at", "OffsetDateTime", False),
            ]),
        },
    },
    "domain4_learning": {
        "schema": "learning",
        "tag": "Domain 4 Learning",
        "mapping": "/api/v1/learning-domain",
        "entities": {
            "LearningPath": ("learning_paths", [
                ("profile_id", "Long"),
                ("title", "String", False),
                ("description", "String"),
                ("difficulty", "String"),
                ("estimated_duration", "Integer"),
                ("status", "String", False),
                ("created_at", "OffsetDateTime", False),
            ]),
            "Course": ("courses", [
                ("learning_path_id", "Long"),
                ("title", "String", False),
                ("description", "String"),
                ("instructor", "String"),
                ("duration_hours", "Integer"),
                ("level", "String"),
                ("published", "Boolean", False),
            ]),
            "CourseModule": ("modules", [
                ("course_id", "Long", False),
                ("title", "String", False),
                ("description", "String"),
                ("sequence", "Integer", False),
            ]),
            "Lesson": ("lessons", [
                ("module_id", "Long", False),
                ("title", "String", False),
                ("lesson_type", "String"),
                ("duration_minutes", "Integer"),
                ("video_url", "String"),
                ("downloadable", "Boolean", False),
            ]),
            "Assignment": ("assignments", [
                ("lesson_id", "Long", False),
                ("title", "String", False),
                ("description", "String"),
                ("due_date", "LocalDate"),
                ("max_score", "Integer"),
            ]),
            "Submission": ("submissions", [
                ("assignment_id", "Long", False),
                ("profile_id", "Long", False),
                ("repository_url", "String"),
                ("file_url", "String"),
                ("score", "Integer"),
                ("status", "String", False),
                ("submitted_at", "OffsetDateTime", False),
            ]),
            "Quiz": ("quizzes", [
                ("lesson_id", "Long", False),
                ("title", "String", False),
                ("total_marks", "Integer", False),
                ("passing_marks", "Integer", False),
                ("duration_minutes", "Integer"),
            ]),
            "QuizAttempt": ("quiz_attempts", [
                ("quiz_id", "Long", False),
                ("profile_id", "Long", False),
                ("obtained_marks", "Integer", False),
                ("passed", "Boolean", False),
                ("attempt_number", "Integer", False),
                ("attempted_at", "OffsetDateTime", False),
            ]),
            "Enrollment": ("enrollments", [
                ("profile_id", "Long", False),
                ("course_id", "Long", False),
                ("enrollment_date", "LocalDate", False),
                ("status", "String", False),
                ("completion_percentage", "Integer", False),
            ]),
            "LearningProgress": ("learning_progress", [
                ("enrollment_id", "Long", False),
                ("lesson_id", "Long", False),
                ("completed", "Boolean", False),
                ("watch_percentage", "Integer", False),
                ("completed_at", "OffsetDateTime"),
            ]),
        },
    },
    "domain5_projects": {
        "schema": "projects",
        "tag": "Domain 5 Projects",
        "mapping": "/api/v1/projects-domain",
        "entities": {
            "Project": ("projects", [
                ("profile_id", "Long", False),
                ("course_id", "Long"),
                ("title", "String", False),
                ("description", "String"),
                ("project_type", "String"),
                ("difficulty_level", "String"),
                ("status", "String", False),
                ("start_date", "LocalDate"),
                ("end_date", "LocalDate"),
                ("github_repository", "String"),
                ("created_at", "OffsetDateTime", False),
            ]),
            "ProjectMilestone": ("project_milestones", [
                ("project_id", "Long", False),
                ("title", "String", False),
                ("description", "String"),
                ("sequence", "Integer", False),
                ("due_date", "LocalDate"),
                ("completed", "Boolean", False),
            ]),
            "ProjectSubmission": ("project_submissions", [
                ("project_id", "Long", False),
                ("profile_id", "Long", False),
                ("repository_url", "String"),
                ("live_demo_url", "String"),
                ("documentation_url", "String"),
                ("submission_status", "String", False),
                ("submitted_at", "OffsetDateTime", False),
            ]),
            "ProjectReview": ("project_reviews", [
                ("submission_id", "Long", False),
                ("reviewer_id", "Long", False),
                ("technical_score", "Integer"),
                ("design_score", "Integer"),
                ("documentation_score", "Integer"),
                ("innovation_score", "Integer"),
                ("overall_score", "Integer"),
                ("feedback", "String"),
                ("reviewed_at", "OffsetDateTime", False),
            ]),
            "PortfolioItem": ("portfolio_items", [
                ("profile_id", "Long", False),
                ("project_id", "Long", False),
                ("title", "String", False),
                ("thumbnail_uri", "String"),
                ("live_url", "String"),
                ("github_url", "String"),
                ("featured", "Boolean", False),
                ("views", "Integer", False),
                ("likes", "Integer", False),
                ("published_at", "OffsetDateTime"),
            ]),
        },
    },
}

for folder, meta in domains.items():
    pkg_entity = f"com.skillops.{folder}.entity"
    pkg_repo = f"com.skillops.{folder}.repository"
    names = []
    for cls, (table, fields) in meta["entities"].items():
        write_entity(pkg_entity, meta["schema"], table, cls, fields)
        write_repo(pkg_repo, cls)
        names.append(cls)
    write_controller(f"com.skillops.{folder}", meta["tag"], meta["mapping"], names)

print("generated")
