package com.skillops.domain6_assessments.controller;

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
import com.skillops.domain6_assessments.entity.Assessment;
import com.skillops.domain6_assessments.entity.AssessmentAttempt;
import com.skillops.domain6_assessments.entity.AssessmentCertificate;
import com.skillops.domain6_assessments.entity.AssessmentFeedback;
import com.skillops.domain6_assessments.entity.AssessmentQuestion;
import com.skillops.domain6_assessments.entity.CodingChallenge;
import com.skillops.domain6_assessments.entity.CodingSubmission;
import com.skillops.domain6_assessments.entity.EvaluationResult;
import com.skillops.domain6_assessments.repository.AssessmentAttemptRepository;
import com.skillops.domain6_assessments.repository.AssessmentCertificateRepository;
import com.skillops.domain6_assessments.repository.AssessmentFeedbackRepository;
import com.skillops.domain6_assessments.repository.AssessmentQuestionRepository;
import com.skillops.domain6_assessments.repository.AssessmentRepository;
import com.skillops.domain6_assessments.repository.CodingChallengeRepository;
import com.skillops.domain6_assessments.repository.CodingSubmissionRepository;
import com.skillops.domain6_assessments.repository.EvaluationResultRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/assessments-domain")
@Tag(name = OpenApiConfig.DOMAIN_6)
public class Domain6AssessmentsController {

    private final CrudFacade crud;
    private final AssessmentRepository assessmentRepository;
    private final AssessmentQuestionRepository assessmentQuestionRepository;
    private final CodingChallengeRepository codingChallengeRepository;
    private final AssessmentAttemptRepository assessmentAttemptRepository;
    private final CodingSubmissionRepository codingSubmissionRepository;
    private final EvaluationResultRepository evaluationResultRepository;
    private final AssessmentCertificateRepository assessmentCertificateRepository;
    private final AssessmentFeedbackRepository assessmentFeedbackRepository;

    public Domain6AssessmentsController(
            CrudFacade crud,
            AssessmentRepository assessmentRepository,
            AssessmentQuestionRepository assessmentQuestionRepository,
            CodingChallengeRepository codingChallengeRepository,
            AssessmentAttemptRepository assessmentAttemptRepository,
            CodingSubmissionRepository codingSubmissionRepository,
            EvaluationResultRepository evaluationResultRepository,
            AssessmentCertificateRepository assessmentCertificateRepository,
            AssessmentFeedbackRepository assessmentFeedbackRepository
    ) {
        this.crud = crud;
        this.assessmentRepository = assessmentRepository;
        this.assessmentQuestionRepository = assessmentQuestionRepository;
        this.codingChallengeRepository = codingChallengeRepository;
        this.assessmentAttemptRepository = assessmentAttemptRepository;
        this.codingSubmissionRepository = codingSubmissionRepository;
        this.evaluationResultRepository = evaluationResultRepository;
        this.assessmentCertificateRepository = assessmentCertificateRepository;
        this.assessmentFeedbackRepository = assessmentFeedbackRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_6);
        body.put("assessments", assessmentRepository.count());
        body.put("assessment-questions", assessmentQuestionRepository.count());
        body.put("coding-challenges", codingChallengeRepository.count());
        body.put("assessment-attempts", assessmentAttemptRepository.count());
        body.put("coding-submissions", codingSubmissionRepository.count());
        body.put("evaluation-results", evaluationResultRepository.count());
        body.put("certificates", assessmentCertificateRepository.count());
        body.put("assessment-feedback", assessmentFeedbackRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/assessments")
    @Operation(summary = "GET all Assessment records")
    public List<Assessment> listAssessment() {
        return crud.findAll(assessmentRepository);
    }

    @GetMapping("/assessments/{id}")
    @Operation(summary = "GET Assessment by id")
    public Assessment getAssessment(@PathVariable Long id) {
        return crud.get(assessmentRepository, id, "Assessment");
    }

    @PostMapping("/assessments")
    @Operation(summary = "POST create Assessment")
    public ResponseEntity<Assessment> createAssessment(@Valid @RequestBody Assessment body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(assessmentRepository, body));
    }

    @PutMapping("/assessments/{id}")
    @Operation(summary = "PUT replace Assessment")
    public Assessment replaceAssessment(@PathVariable Long id, @Valid @RequestBody Assessment body) {
        return crud.replace(assessmentRepository, id, body, "Assessment");
    }

    @PatchMapping("/assessments/{id}")
    @Operation(summary = "PATCH Assessment")
    public Assessment patchAssessment(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(assessmentRepository, id, updates, "Assessment");
    }

    @DeleteMapping("/assessments/{id}")
    @Operation(summary = "DELETE Assessment")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
        crud.delete(assessmentRepository, id, "Assessment");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assessment-questions")
    @Operation(summary = "GET all AssessmentQuestion records")
    public List<AssessmentQuestion> listAssessmentQuestion() {
        return crud.findAll(assessmentQuestionRepository);
    }

    @GetMapping("/assessment-questions/{id}")
    @Operation(summary = "GET AssessmentQuestion by id")
    public AssessmentQuestion getAssessmentQuestion(@PathVariable Long id) {
        return crud.get(assessmentQuestionRepository, id, "AssessmentQuestion");
    }

    @PostMapping("/assessment-questions")
    @Operation(summary = "POST create AssessmentQuestion")
    public ResponseEntity<AssessmentQuestion> createAssessmentQuestion(@Valid @RequestBody AssessmentQuestion body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(assessmentQuestionRepository, body));
    }

    @PutMapping("/assessment-questions/{id}")
    @Operation(summary = "PUT replace AssessmentQuestion")
    public AssessmentQuestion replaceAssessmentQuestion(@PathVariable Long id, @Valid @RequestBody AssessmentQuestion body) {
        return crud.replace(assessmentQuestionRepository, id, body, "AssessmentQuestion");
    }

    @PatchMapping("/assessment-questions/{id}")
    @Operation(summary = "PATCH AssessmentQuestion")
    public AssessmentQuestion patchAssessmentQuestion(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(assessmentQuestionRepository, id, updates, "AssessmentQuestion");
    }

    @DeleteMapping("/assessment-questions/{id}")
    @Operation(summary = "DELETE AssessmentQuestion")
    public ResponseEntity<Void> deleteAssessmentQuestion(@PathVariable Long id) {
        crud.delete(assessmentQuestionRepository, id, "AssessmentQuestion");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/coding-challenges")
    @Operation(summary = "GET all CodingChallenge records")
    public List<CodingChallenge> listCodingChallenge() {
        return crud.findAll(codingChallengeRepository);
    }

    @GetMapping("/coding-challenges/{id}")
    @Operation(summary = "GET CodingChallenge by id")
    public CodingChallenge getCodingChallenge(@PathVariable Long id) {
        return crud.get(codingChallengeRepository, id, "CodingChallenge");
    }

    @PostMapping("/coding-challenges")
    @Operation(summary = "POST create CodingChallenge")
    public ResponseEntity<CodingChallenge> createCodingChallenge(@Valid @RequestBody CodingChallenge body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(codingChallengeRepository, body));
    }

    @PutMapping("/coding-challenges/{id}")
    @Operation(summary = "PUT replace CodingChallenge")
    public CodingChallenge replaceCodingChallenge(@PathVariable Long id, @Valid @RequestBody CodingChallenge body) {
        return crud.replace(codingChallengeRepository, id, body, "CodingChallenge");
    }

    @PatchMapping("/coding-challenges/{id}")
    @Operation(summary = "PATCH CodingChallenge")
    public CodingChallenge patchCodingChallenge(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(codingChallengeRepository, id, updates, "CodingChallenge");
    }

    @DeleteMapping("/coding-challenges/{id}")
    @Operation(summary = "DELETE CodingChallenge")
    public ResponseEntity<Void> deleteCodingChallenge(@PathVariable Long id) {
        crud.delete(codingChallengeRepository, id, "CodingChallenge");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assessment-attempts")
    @Operation(summary = "GET all AssessmentAttempt records")
    public List<AssessmentAttempt> listAssessmentAttempt() {
        return crud.findAll(assessmentAttemptRepository);
    }

    @GetMapping("/assessment-attempts/{id}")
    @Operation(summary = "GET AssessmentAttempt by id")
    public AssessmentAttempt getAssessmentAttempt(@PathVariable Long id) {
        return crud.get(assessmentAttemptRepository, id, "AssessmentAttempt");
    }

    @PostMapping("/assessment-attempts")
    @Operation(summary = "POST create AssessmentAttempt")
    public ResponseEntity<AssessmentAttempt> createAssessmentAttempt(@Valid @RequestBody AssessmentAttempt body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(assessmentAttemptRepository, body));
    }

    @PutMapping("/assessment-attempts/{id}")
    @Operation(summary = "PUT replace AssessmentAttempt")
    public AssessmentAttempt replaceAssessmentAttempt(@PathVariable Long id, @Valid @RequestBody AssessmentAttempt body) {
        return crud.replace(assessmentAttemptRepository, id, body, "AssessmentAttempt");
    }

    @PatchMapping("/assessment-attempts/{id}")
    @Operation(summary = "PATCH AssessmentAttempt")
    public AssessmentAttempt patchAssessmentAttempt(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(assessmentAttemptRepository, id, updates, "AssessmentAttempt");
    }

    @DeleteMapping("/assessment-attempts/{id}")
    @Operation(summary = "DELETE AssessmentAttempt")
    public ResponseEntity<Void> deleteAssessmentAttempt(@PathVariable Long id) {
        crud.delete(assessmentAttemptRepository, id, "AssessmentAttempt");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/coding-submissions")
    @Operation(summary = "GET all CodingSubmission records")
    public List<CodingSubmission> listCodingSubmission() {
        return crud.findAll(codingSubmissionRepository);
    }

    @GetMapping("/coding-submissions/{id}")
    @Operation(summary = "GET CodingSubmission by id")
    public CodingSubmission getCodingSubmission(@PathVariable Long id) {
        return crud.get(codingSubmissionRepository, id, "CodingSubmission");
    }

    @PostMapping("/coding-submissions")
    @Operation(summary = "POST create CodingSubmission")
    public ResponseEntity<CodingSubmission> createCodingSubmission(@Valid @RequestBody CodingSubmission body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(codingSubmissionRepository, body));
    }

    @PutMapping("/coding-submissions/{id}")
    @Operation(summary = "PUT replace CodingSubmission")
    public CodingSubmission replaceCodingSubmission(@PathVariable Long id, @Valid @RequestBody CodingSubmission body) {
        return crud.replace(codingSubmissionRepository, id, body, "CodingSubmission");
    }

    @PatchMapping("/coding-submissions/{id}")
    @Operation(summary = "PATCH CodingSubmission")
    public CodingSubmission patchCodingSubmission(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(codingSubmissionRepository, id, updates, "CodingSubmission");
    }

    @DeleteMapping("/coding-submissions/{id}")
    @Operation(summary = "DELETE CodingSubmission")
    public ResponseEntity<Void> deleteCodingSubmission(@PathVariable Long id) {
        crud.delete(codingSubmissionRepository, id, "CodingSubmission");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/evaluation-results")
    @Operation(summary = "GET all EvaluationResult records")
    public List<EvaluationResult> listEvaluationResult() {
        return crud.findAll(evaluationResultRepository);
    }

    @GetMapping("/evaluation-results/{id}")
    @Operation(summary = "GET EvaluationResult by id")
    public EvaluationResult getEvaluationResult(@PathVariable Long id) {
        return crud.get(evaluationResultRepository, id, "EvaluationResult");
    }

    @PostMapping("/evaluation-results")
    @Operation(summary = "POST create EvaluationResult")
    public ResponseEntity<EvaluationResult> createEvaluationResult(@Valid @RequestBody EvaluationResult body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(evaluationResultRepository, body));
    }

    @PutMapping("/evaluation-results/{id}")
    @Operation(summary = "PUT replace EvaluationResult")
    public EvaluationResult replaceEvaluationResult(@PathVariable Long id, @Valid @RequestBody EvaluationResult body) {
        return crud.replace(evaluationResultRepository, id, body, "EvaluationResult");
    }

    @PatchMapping("/evaluation-results/{id}")
    @Operation(summary = "PATCH EvaluationResult")
    public EvaluationResult patchEvaluationResult(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(evaluationResultRepository, id, updates, "EvaluationResult");
    }

    @DeleteMapping("/evaluation-results/{id}")
    @Operation(summary = "DELETE EvaluationResult")
    public ResponseEntity<Void> deleteEvaluationResult(@PathVariable Long id) {
        crud.delete(evaluationResultRepository, id, "EvaluationResult");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/certificates")
    @Operation(summary = "GET all AssessmentCertificate records")
    public List<AssessmentCertificate> listAssessmentCertificate() {
        return crud.findAll(assessmentCertificateRepository);
    }

    @GetMapping("/certificates/{id}")
    @Operation(summary = "GET AssessmentCertificate by id")
    public AssessmentCertificate getAssessmentCertificate(@PathVariable Long id) {
        return crud.get(assessmentCertificateRepository, id, "AssessmentCertificate");
    }

    @PostMapping("/certificates")
    @Operation(summary = "POST create AssessmentCertificate")
    public ResponseEntity<AssessmentCertificate> createAssessmentCertificate(@Valid @RequestBody AssessmentCertificate body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(assessmentCertificateRepository, body));
    }

    @PutMapping("/certificates/{id}")
    @Operation(summary = "PUT replace AssessmentCertificate")
    public AssessmentCertificate replaceAssessmentCertificate(@PathVariable Long id, @Valid @RequestBody AssessmentCertificate body) {
        return crud.replace(assessmentCertificateRepository, id, body, "AssessmentCertificate");
    }

    @PatchMapping("/certificates/{id}")
    @Operation(summary = "PATCH AssessmentCertificate")
    public AssessmentCertificate patchAssessmentCertificate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(assessmentCertificateRepository, id, updates, "AssessmentCertificate");
    }

    @DeleteMapping("/certificates/{id}")
    @Operation(summary = "DELETE AssessmentCertificate")
    public ResponseEntity<Void> deleteAssessmentCertificate(@PathVariable Long id) {
        crud.delete(assessmentCertificateRepository, id, "AssessmentCertificate");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assessment-feedback")
    @Operation(summary = "GET all AssessmentFeedback records")
    public List<AssessmentFeedback> listAssessmentFeedback() {
        return crud.findAll(assessmentFeedbackRepository);
    }

    @GetMapping("/assessment-feedback/{id}")
    @Operation(summary = "GET AssessmentFeedback by id")
    public AssessmentFeedback getAssessmentFeedback(@PathVariable Long id) {
        return crud.get(assessmentFeedbackRepository, id, "AssessmentFeedback");
    }

    @PostMapping("/assessment-feedback")
    @Operation(summary = "POST create AssessmentFeedback")
    public ResponseEntity<AssessmentFeedback> createAssessmentFeedback(@Valid @RequestBody AssessmentFeedback body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(assessmentFeedbackRepository, body));
    }

    @PutMapping("/assessment-feedback/{id}")
    @Operation(summary = "PUT replace AssessmentFeedback")
    public AssessmentFeedback replaceAssessmentFeedback(@PathVariable Long id, @Valid @RequestBody AssessmentFeedback body) {
        return crud.replace(assessmentFeedbackRepository, id, body, "AssessmentFeedback");
    }

    @PatchMapping("/assessment-feedback/{id}")
    @Operation(summary = "PATCH AssessmentFeedback")
    public AssessmentFeedback patchAssessmentFeedback(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(assessmentFeedbackRepository, id, updates, "AssessmentFeedback");
    }

    @DeleteMapping("/assessment-feedback/{id}")
    @Operation(summary = "DELETE AssessmentFeedback")
    public ResponseEntity<Void> deleteAssessmentFeedback(@PathVariable Long id) {
        crud.delete(assessmentFeedbackRepository, id, "AssessmentFeedback");
        return ResponseEntity.noContent().build();
    }

}
