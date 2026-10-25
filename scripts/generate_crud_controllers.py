from pathlib import Path

ROOT = Path(r"d:\SkillsOps AI Powered Workforce Development and Hiring Operating System\src\main\java\com\skillops")

CONTROLLERS = [
    {
        "file": ROOT / "domain2_user_profiles" / "controller" / "Domain2UserProfilesController.java",
        "pkg": "com.skillops.domain2_user_profiles",
        "class": "Domain2UserProfilesController",
        "tag": "DOMAIN_2",
        "mapping": "/api/v1/profiles-domain",
        "resources": [
            ("profiles", "Profile", "profileRepository", "ProfileRepository"),
            ("resumes", "Resume", "resumeRepository", "ResumeRepository"),
            ("educations", "Education", "educationRepository", "EducationRepository"),
            ("experiences", "Experience", "experienceRepository", "ExperienceRepository"),
            ("profile-certifications", "ProfileCertification", "profileCertificationRepository", "ProfileCertificationRepository"),
            ("career-goals", "CareerGoal", "careerGoalRepository", "CareerGoalRepository"),
            ("skill-passports", "SkillPassport", "skillPassportRepository", "SkillPassportRepository"),
        ],
    },
    {
        "file": ROOT / "domain3_skills" / "controller" / "Domain3SkillsController.java",
        "pkg": "com.skillops.domain3_skills",
        "class": "Domain3SkillsController",
        "tag": "DOMAIN_3",
        "mapping": "/api/v1/skills-domain",
        "resources": [
            ("skill-categories", "SkillCategory", "skillCategoryRepository", "SkillCategoryRepository"),
            ("skills", "Skill", "skillRepository", "SkillRepository"),
            ("skill-assessments", "SkillAssessment", "skillAssessmentRepository", "SkillAssessmentRepository"),
            ("competencies", "Competency", "competencyRepository", "CompetencyRepository"),
            ("competency-levels", "CompetencyLevel", "competencyLevelRepository", "CompetencyLevelRepository"),
            ("user-skills", "UserSkill", "userSkillRepository", "UserSkillRepository"),
            ("skill-gaps", "SkillGap", "skillGapRepository", "SkillGapRepository"),
            ("skill-recommendations", "SkillRecommendation", "skillRecommendationRepository", "SkillRecommendationRepository"),
        ],
    },
    {
        "file": ROOT / "domain4_learning" / "controller" / "Domain4LearningController.java",
        "pkg": "com.skillops.domain4_learning",
        "class": "Domain4LearningController",
        "tag": "DOMAIN_4",
        "mapping": "/api/v1/learning-domain",
        "resources": [
            ("learning-paths", "LearningPath", "learningPathRepository", "LearningPathRepository"),
            ("courses", "Course", "courseRepository", "CourseRepository"),
            ("course-modules", "CourseModule", "courseModuleRepository", "CourseModuleRepository"),
            ("lessons", "Lesson", "lessonRepository", "LessonRepository"),
            ("assignments", "Assignment", "assignmentRepository", "AssignmentRepository"),
            ("submissions", "Submission", "submissionRepository", "SubmissionRepository"),
            ("quizzes", "Quiz", "quizRepository", "QuizRepository"),
            ("quiz-attempts", "QuizAttempt", "quizAttemptRepository", "QuizAttemptRepository"),
            ("enrollments", "Enrollment", "enrollmentRepository", "EnrollmentRepository"),
            ("learning-progress", "LearningProgress", "learningProgressRepository", "LearningProgressRepository"),
        ],
    },
    {
        "file": ROOT / "domain5_projects" / "controller" / "Domain5ProjectsController.java",
        "pkg": "com.skillops.domain5_projects",
        "class": "Domain5ProjectsController",
        "tag": "DOMAIN_5",
        "mapping": "/api/v1/projects-domain",
        "resources": [
            ("projects", "Project", "projectRepository", "ProjectRepository"),
            ("project-milestones", "ProjectMilestone", "projectMilestoneRepository", "ProjectMilestoneRepository"),
            ("project-submissions", "ProjectSubmission", "projectSubmissionRepository", "ProjectSubmissionRepository"),
            ("project-reviews", "ProjectReview", "projectReviewRepository", "ProjectReviewRepository"),
            ("portfolio-items", "PortfolioItem", "portfolioItemRepository", "PortfolioItemRepository"),
        ],
    },
]


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
    public ResponseEntity<{cls}> create{cls}(@RequestBody {cls} body) {{
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create({var}, body));
    }}

    @PutMapping("/{path}/{{id}}")
    @Operation(summary = "PUT replace {cls}")
    public {cls} replace{cls}(@PathVariable Long id, @RequestBody {cls} body) {{
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


for spec in CONTROLLERS:
    imports = []
    fields = ["    private final CrudFacade crud;"]
    ctor_args = ["CrudFacade crud"]
    assigns = ["        this.crud = crud;"]
    counts = []
    methods = []
    for path, cls, var, repo in spec["resources"]:
        imports.append(f"import {spec['pkg']}.entity.{cls};")
        imports.append(f"import {spec['pkg']}.repository.{repo};")
        fields.append(f"    private final {repo} {var};")
        ctor_args.append(f"{repo} {var}")
        assigns.append(f"        this.{var} = {var};")
        counts.append(f'        body.put("{path}", {var}.count());')
        methods.append(resource_block(path, cls, var))

    text = f"""package {spec['pkg']}.controller;

import com.skillops.common.CrudFacade;
import com.skillops.config.OpenApiConfig;
{chr(10).join(imports)}
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("{spec['mapping']}")
@Tag(name = OpenApiConfig.{spec['tag']})
public class {spec['class']} {{

{chr(10).join(fields)}

    public {spec['class']}(
            {','.join(chr(10) + '            ' + a for a in ctor_args)}
    ) {{
{chr(10).join(assigns)}
    }}

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {{
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.{spec['tag']});
{chr(10).join(counts)}
        return ResponseEntity.ok(body);
    }}
{''.join(methods)}
}}
"""
    spec["file"].write_text(text, encoding="utf-8")
    print("wrote", spec["file"].name)

print("done")
