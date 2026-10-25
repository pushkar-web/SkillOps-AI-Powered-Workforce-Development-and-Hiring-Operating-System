package com.skillops.domain3_skills.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import com.skillops.common.CrudFacade;
import com.skillops.config.OpenApiConfig;
import com.skillops.domain3_skills.entity.Competency;
import com.skillops.domain3_skills.entity.CompetencyLevel;
import com.skillops.domain3_skills.entity.Skill;
import com.skillops.domain3_skills.entity.SkillAssessment;
import com.skillops.domain3_skills.entity.SkillCategory;
import com.skillops.domain3_skills.entity.SkillGap;
import com.skillops.domain3_skills.entity.SkillRecommendation;
import com.skillops.domain3_skills.entity.UserSkill;
import com.skillops.domain3_skills.repository.CompetencyLevelRepository;
import com.skillops.domain3_skills.repository.CompetencyRepository;
import com.skillops.domain3_skills.repository.SkillAssessmentRepository;
import com.skillops.domain3_skills.repository.SkillCategoryRepository;
import com.skillops.domain3_skills.repository.SkillGapRepository;
import com.skillops.domain3_skills.repository.SkillRecommendationRepository;
import com.skillops.domain3_skills.repository.SkillRepository;
import com.skillops.domain3_skills.repository.UserSkillRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/skills-domain")
@Tag(name = OpenApiConfig.DOMAIN_3)
public class Domain3SkillsController {

    private final CrudFacade crud;
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillRepository skillRepository;
    private final SkillAssessmentRepository skillAssessmentRepository;
    private final CompetencyRepository competencyRepository;
    private final CompetencyLevelRepository competencyLevelRepository;
    private final UserSkillRepository userSkillRepository;
    private final SkillGapRepository skillGapRepository;
    private final SkillRecommendationRepository skillRecommendationRepository;

    public Domain3SkillsController(
            
            CrudFacade crud,
            SkillCategoryRepository skillCategoryRepository,
            SkillRepository skillRepository,
            SkillAssessmentRepository skillAssessmentRepository,
            CompetencyRepository competencyRepository,
            CompetencyLevelRepository competencyLevelRepository,
            UserSkillRepository userSkillRepository,
            SkillGapRepository skillGapRepository,
            SkillRecommendationRepository skillRecommendationRepository
    ) {
        this.crud = crud;
        this.skillCategoryRepository = skillCategoryRepository;
        this.skillRepository = skillRepository;
        this.skillAssessmentRepository = skillAssessmentRepository;
        this.competencyRepository = competencyRepository;
        this.competencyLevelRepository = competencyLevelRepository;
        this.userSkillRepository = userSkillRepository;
        this.skillGapRepository = skillGapRepository;
        this.skillRecommendationRepository = skillRecommendationRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_3);
        body.put("skill-categories", skillCategoryRepository.count());
        body.put("skills", skillRepository.count());
        body.put("skill-assessments", skillAssessmentRepository.count());
        body.put("competencies", competencyRepository.count());
        body.put("competency-levels", competencyLevelRepository.count());
        body.put("user-skills", userSkillRepository.count());
        body.put("skill-gaps", skillGapRepository.count());
        body.put("skill-recommendations", skillRecommendationRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/skill-categories")
    @Operation(summary = "GET all SkillCategory records")
    public List<SkillCategory> listSkillCategory() {
        return crud.findAll(skillCategoryRepository);
    }

    @GetMapping("/skill-categories/{id}")
    @Operation(summary = "GET SkillCategory by id")
    public SkillCategory getSkillCategory(@PathVariable Long id) {
        return crud.get(skillCategoryRepository, id, "SkillCategory");
    }

    @PostMapping("/skill-categories")
    @Operation(summary = "POST create SkillCategory")
    public ResponseEntity<SkillCategory> createSkillCategory(@Valid @RequestBody SkillCategory body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(skillCategoryRepository, body));
    }

    @PutMapping("/skill-categories/{id}")
    @Operation(summary = "PUT replace SkillCategory")
    public SkillCategory replaceSkillCategory(@PathVariable Long id, @Valid @RequestBody SkillCategory body) {
        return crud.replace(skillCategoryRepository, id, body, "SkillCategory");
    }

    @PatchMapping("/skill-categories/{id}")
    @Operation(summary = "PATCH SkillCategory")
    public SkillCategory patchSkillCategory(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(skillCategoryRepository, id, updates, "SkillCategory");
    }

    @DeleteMapping("/skill-categories/{id}")
    @Operation(summary = "DELETE SkillCategory")
    public ResponseEntity<Void> deleteSkillCategory(@PathVariable Long id) {
        crud.delete(skillCategoryRepository, id, "SkillCategory");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/skills")
    @Operation(summary = "GET all Skill records")
    public List<Skill> listSkill() {
        return crud.findAll(skillRepository);
    }

    @GetMapping("/skills/{id}")
    @Operation(summary = "GET Skill by id")
    public Skill getSkill(@PathVariable Long id) {
        return crud.get(skillRepository, id, "Skill");
    }

    @PostMapping("/skills")
    @Operation(summary = "POST create Skill")
    public ResponseEntity<Skill> createSkill(@Valid @RequestBody Skill body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(skillRepository, body));
    }

    @PutMapping("/skills/{id}")
    @Operation(summary = "PUT replace Skill")
    public Skill replaceSkill(@PathVariable Long id, @Valid @RequestBody Skill body) {
        return crud.replace(skillRepository, id, body, "Skill");
    }

    @PatchMapping("/skills/{id}")
    @Operation(summary = "PATCH Skill")
    public Skill patchSkill(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(skillRepository, id, updates, "Skill");
    }

    @DeleteMapping("/skills/{id}")
    @Operation(summary = "DELETE Skill")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        crud.delete(skillRepository, id, "Skill");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/skill-assessments")
    @Operation(summary = "GET all SkillAssessment records")
    public List<SkillAssessment> listSkillAssessment() {
        return crud.findAll(skillAssessmentRepository);
    }

    @GetMapping("/skill-assessments/{id}")
    @Operation(summary = "GET SkillAssessment by id")
    public SkillAssessment getSkillAssessment(@PathVariable Long id) {
        return crud.get(skillAssessmentRepository, id, "SkillAssessment");
    }

    @PostMapping("/skill-assessments")
    @Operation(summary = "POST create SkillAssessment")
    public ResponseEntity<SkillAssessment> createSkillAssessment(@Valid @RequestBody SkillAssessment body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(skillAssessmentRepository, body));
    }

    @PutMapping("/skill-assessments/{id}")
    @Operation(summary = "PUT replace SkillAssessment")
    public SkillAssessment replaceSkillAssessment(@PathVariable Long id, @Valid @RequestBody SkillAssessment body) {
        return crud.replace(skillAssessmentRepository, id, body, "SkillAssessment");
    }

    @PatchMapping("/skill-assessments/{id}")
    @Operation(summary = "PATCH SkillAssessment")
    public SkillAssessment patchSkillAssessment(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(skillAssessmentRepository, id, updates, "SkillAssessment");
    }

    @DeleteMapping("/skill-assessments/{id}")
    @Operation(summary = "DELETE SkillAssessment")
    public ResponseEntity<Void> deleteSkillAssessment(@PathVariable Long id) {
        crud.delete(skillAssessmentRepository, id, "SkillAssessment");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/competencies")
    @Operation(summary = "GET all Competency records")
    public List<Competency> listCompetency() {
        return crud.findAll(competencyRepository);
    }

    @GetMapping("/competencies/{id}")
    @Operation(summary = "GET Competency by id")
    public Competency getCompetency(@PathVariable Long id) {
        return crud.get(competencyRepository, id, "Competency");
    }

    @PostMapping("/competencies")
    @Operation(summary = "POST create Competency")
    public ResponseEntity<Competency> createCompetency(@Valid @RequestBody Competency body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(competencyRepository, body));
    }

    @PutMapping("/competencies/{id}")
    @Operation(summary = "PUT replace Competency")
    public Competency replaceCompetency(@PathVariable Long id, @Valid @RequestBody Competency body) {
        return crud.replace(competencyRepository, id, body, "Competency");
    }

    @PatchMapping("/competencies/{id}")
    @Operation(summary = "PATCH Competency")
    public Competency patchCompetency(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(competencyRepository, id, updates, "Competency");
    }

    @DeleteMapping("/competencies/{id}")
    @Operation(summary = "DELETE Competency")
    public ResponseEntity<Void> deleteCompetency(@PathVariable Long id) {
        crud.delete(competencyRepository, id, "Competency");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/competency-levels")
    @Operation(summary = "GET all CompetencyLevel records")
    public List<CompetencyLevel> listCompetencyLevel() {
        return crud.findAll(competencyLevelRepository);
    }

    @GetMapping("/competency-levels/{id}")
    @Operation(summary = "GET CompetencyLevel by id")
    public CompetencyLevel getCompetencyLevel(@PathVariable Long id) {
        return crud.get(competencyLevelRepository, id, "CompetencyLevel");
    }

    @PostMapping("/competency-levels")
    @Operation(summary = "POST create CompetencyLevel")
    public ResponseEntity<CompetencyLevel> createCompetencyLevel(@Valid @RequestBody CompetencyLevel body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(competencyLevelRepository, body));
    }

    @PutMapping("/competency-levels/{id}")
    @Operation(summary = "PUT replace CompetencyLevel")
    public CompetencyLevel replaceCompetencyLevel(@PathVariable Long id, @Valid @RequestBody CompetencyLevel body) {
        return crud.replace(competencyLevelRepository, id, body, "CompetencyLevel");
    }

    @PatchMapping("/competency-levels/{id}")
    @Operation(summary = "PATCH CompetencyLevel")
    public CompetencyLevel patchCompetencyLevel(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(competencyLevelRepository, id, updates, "CompetencyLevel");
    }

    @DeleteMapping("/competency-levels/{id}")
    @Operation(summary = "DELETE CompetencyLevel")
    public ResponseEntity<Void> deleteCompetencyLevel(@PathVariable Long id) {
        crud.delete(competencyLevelRepository, id, "CompetencyLevel");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user-skills")
    @Operation(summary = "GET all UserSkill records")
    public List<UserSkill> listUserSkill() {
        return crud.findAll(userSkillRepository);
    }

    @GetMapping("/user-skills/{id}")
    @Operation(summary = "GET UserSkill by id")
    public UserSkill getUserSkill(@PathVariable Long id) {
        return crud.get(userSkillRepository, id, "UserSkill");
    }

    @PostMapping("/user-skills")
    @Operation(summary = "POST create UserSkill")
    public ResponseEntity<UserSkill> createUserSkill(@Valid @RequestBody UserSkill body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(userSkillRepository, body));
    }

    @PutMapping("/user-skills/{id}")
    @Operation(summary = "PUT replace UserSkill")
    public UserSkill replaceUserSkill(@PathVariable Long id, @Valid @RequestBody UserSkill body) {
        return crud.replace(userSkillRepository, id, body, "UserSkill");
    }

    @PatchMapping("/user-skills/{id}")
    @Operation(summary = "PATCH UserSkill")
    public UserSkill patchUserSkill(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(userSkillRepository, id, updates, "UserSkill");
    }

    @DeleteMapping("/user-skills/{id}")
    @Operation(summary = "DELETE UserSkill")
    public ResponseEntity<Void> deleteUserSkill(@PathVariable Long id) {
        crud.delete(userSkillRepository, id, "UserSkill");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/skill-gaps")
    @Operation(summary = "GET all SkillGap records")
    public List<SkillGap> listSkillGap() {
        return crud.findAll(skillGapRepository);
    }

    @GetMapping("/skill-gaps/{id}")
    @Operation(summary = "GET SkillGap by id")
    public SkillGap getSkillGap(@PathVariable Long id) {
        return crud.get(skillGapRepository, id, "SkillGap");
    }

    @PostMapping("/skill-gaps")
    @Operation(summary = "POST create SkillGap")
    public ResponseEntity<SkillGap> createSkillGap(@Valid @RequestBody SkillGap body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(skillGapRepository, body));
    }

    @PutMapping("/skill-gaps/{id}")
    @Operation(summary = "PUT replace SkillGap")
    public SkillGap replaceSkillGap(@PathVariable Long id, @Valid @RequestBody SkillGap body) {
        return crud.replace(skillGapRepository, id, body, "SkillGap");
    }

    @PatchMapping("/skill-gaps/{id}")
    @Operation(summary = "PATCH SkillGap")
    public SkillGap patchSkillGap(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(skillGapRepository, id, updates, "SkillGap");
    }

    @DeleteMapping("/skill-gaps/{id}")
    @Operation(summary = "DELETE SkillGap")
    public ResponseEntity<Void> deleteSkillGap(@PathVariable Long id) {
        crud.delete(skillGapRepository, id, "SkillGap");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/skill-recommendations")
    @Operation(summary = "GET all SkillRecommendation records")
    public List<SkillRecommendation> listSkillRecommendation() {
        return crud.findAll(skillRecommendationRepository);
    }

    @GetMapping("/skill-recommendations/{id}")
    @Operation(summary = "GET SkillRecommendation by id")
    public SkillRecommendation getSkillRecommendation(@PathVariable Long id) {
        return crud.get(skillRecommendationRepository, id, "SkillRecommendation");
    }

    @PostMapping("/skill-recommendations")
    @Operation(summary = "POST create SkillRecommendation")
    public ResponseEntity<SkillRecommendation> createSkillRecommendation(@Valid @RequestBody SkillRecommendation body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(skillRecommendationRepository, body));
    }

    @PutMapping("/skill-recommendations/{id}")
    @Operation(summary = "PUT replace SkillRecommendation")
    public SkillRecommendation replaceSkillRecommendation(@PathVariable Long id, @Valid @RequestBody SkillRecommendation body) {
        return crud.replace(skillRecommendationRepository, id, body, "SkillRecommendation");
    }

    @PatchMapping("/skill-recommendations/{id}")
    @Operation(summary = "PATCH SkillRecommendation")
    public SkillRecommendation patchSkillRecommendation(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(skillRecommendationRepository, id, updates, "SkillRecommendation");
    }

    @DeleteMapping("/skill-recommendations/{id}")
    @Operation(summary = "DELETE SkillRecommendation")
    public ResponseEntity<Void> deleteSkillRecommendation(@PathVariable Long id) {
        crud.delete(skillRecommendationRepository, id, "SkillRecommendation");
        return ResponseEntity.noContent().build();
    }

}
