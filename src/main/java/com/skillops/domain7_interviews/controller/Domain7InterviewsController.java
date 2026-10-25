package com.skillops.domain7_interviews.controller;

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
import com.skillops.domain7_interviews.entity.AiInterviewReport;
import com.skillops.domain7_interviews.entity.Interview;
import com.skillops.domain7_interviews.entity.InterviewFeedback;
import com.skillops.domain7_interviews.entity.InterviewRound;
import com.skillops.domain7_interviews.entity.InterviewScorecard;
import com.skillops.domain7_interviews.repository.AiInterviewReportRepository;
import com.skillops.domain7_interviews.repository.InterviewFeedbackRepository;
import com.skillops.domain7_interviews.repository.InterviewRepository;
import com.skillops.domain7_interviews.repository.InterviewRoundRepository;
import com.skillops.domain7_interviews.repository.InterviewScorecardRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/interviews-domain")
@Tag(name = OpenApiConfig.DOMAIN_7)
public class Domain7InterviewsController {

    private final CrudFacade crud;
    private final InterviewRepository interviewRepository;
    private final InterviewRoundRepository interviewRoundRepository;
    private final InterviewFeedbackRepository interviewFeedbackRepository;
    private final InterviewScorecardRepository interviewScorecardRepository;
    private final AiInterviewReportRepository aiInterviewReportRepository;

    public Domain7InterviewsController(
            CrudFacade crud,
            InterviewRepository interviewRepository,
            InterviewRoundRepository interviewRoundRepository,
            InterviewFeedbackRepository interviewFeedbackRepository,
            InterviewScorecardRepository interviewScorecardRepository,
            AiInterviewReportRepository aiInterviewReportRepository
    ) {
        this.crud = crud;
        this.interviewRepository = interviewRepository;
        this.interviewRoundRepository = interviewRoundRepository;
        this.interviewFeedbackRepository = interviewFeedbackRepository;
        this.interviewScorecardRepository = interviewScorecardRepository;
        this.aiInterviewReportRepository = aiInterviewReportRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_7);
        body.put("interviews", interviewRepository.count());
        body.put("interview-rounds", interviewRoundRepository.count());
        body.put("interview-feedback", interviewFeedbackRepository.count());
        body.put("interview-scorecards", interviewScorecardRepository.count());
        body.put("ai-interview-reports", aiInterviewReportRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/interviews")
    @Operation(summary = "GET all Interview records")
    public List<Interview> listInterview() {
        return crud.findAll(interviewRepository);
    }

    @GetMapping("/interviews/{id}")
    @Operation(summary = "GET Interview by id")
    public Interview getInterview(@PathVariable Long id) {
        return crud.get(interviewRepository, id, "Interview");
    }

    @PostMapping("/interviews")
    @Operation(summary = "POST create Interview")
    public ResponseEntity<Interview> createInterview(@Valid @RequestBody Interview body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(interviewRepository, body));
    }

    @PutMapping("/interviews/{id}")
    @Operation(summary = "PUT replace Interview")
    public Interview replaceInterview(@PathVariable Long id, @Valid @RequestBody Interview body) {
        return crud.replace(interviewRepository, id, body, "Interview");
    }

    @PatchMapping("/interviews/{id}")
    @Operation(summary = "PATCH Interview")
    public Interview patchInterview(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(interviewRepository, id, updates, "Interview");
    }

    @DeleteMapping("/interviews/{id}")
    @Operation(summary = "DELETE Interview")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id) {
        crud.delete(interviewRepository, id, "Interview");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/interview-rounds")
    @Operation(summary = "GET all InterviewRound records")
    public List<InterviewRound> listInterviewRound() {
        return crud.findAll(interviewRoundRepository);
    }

    @GetMapping("/interview-rounds/{id}")
    @Operation(summary = "GET InterviewRound by id")
    public InterviewRound getInterviewRound(@PathVariable Long id) {
        return crud.get(interviewRoundRepository, id, "InterviewRound");
    }

    @PostMapping("/interview-rounds")
    @Operation(summary = "POST create InterviewRound")
    public ResponseEntity<InterviewRound> createInterviewRound(@Valid @RequestBody InterviewRound body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(interviewRoundRepository, body));
    }

    @PutMapping("/interview-rounds/{id}")
    @Operation(summary = "PUT replace InterviewRound")
    public InterviewRound replaceInterviewRound(@PathVariable Long id, @Valid @RequestBody InterviewRound body) {
        return crud.replace(interviewRoundRepository, id, body, "InterviewRound");
    }

    @PatchMapping("/interview-rounds/{id}")
    @Operation(summary = "PATCH InterviewRound")
    public InterviewRound patchInterviewRound(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(interviewRoundRepository, id, updates, "InterviewRound");
    }

    @DeleteMapping("/interview-rounds/{id}")
    @Operation(summary = "DELETE InterviewRound")
    public ResponseEntity<Void> deleteInterviewRound(@PathVariable Long id) {
        crud.delete(interviewRoundRepository, id, "InterviewRound");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/interview-feedback")
    @Operation(summary = "GET all InterviewFeedback records")
    public List<InterviewFeedback> listInterviewFeedback() {
        return crud.findAll(interviewFeedbackRepository);
    }

    @GetMapping("/interview-feedback/{id}")
    @Operation(summary = "GET InterviewFeedback by id")
    public InterviewFeedback getInterviewFeedback(@PathVariable Long id) {
        return crud.get(interviewFeedbackRepository, id, "InterviewFeedback");
    }

    @PostMapping("/interview-feedback")
    @Operation(summary = "POST create InterviewFeedback")
    public ResponseEntity<InterviewFeedback> createInterviewFeedback(@Valid @RequestBody InterviewFeedback body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(interviewFeedbackRepository, body));
    }

    @PutMapping("/interview-feedback/{id}")
    @Operation(summary = "PUT replace InterviewFeedback")
    public InterviewFeedback replaceInterviewFeedback(@PathVariable Long id, @Valid @RequestBody InterviewFeedback body) {
        return crud.replace(interviewFeedbackRepository, id, body, "InterviewFeedback");
    }

    @PatchMapping("/interview-feedback/{id}")
    @Operation(summary = "PATCH InterviewFeedback")
    public InterviewFeedback patchInterviewFeedback(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(interviewFeedbackRepository, id, updates, "InterviewFeedback");
    }

    @DeleteMapping("/interview-feedback/{id}")
    @Operation(summary = "DELETE InterviewFeedback")
    public ResponseEntity<Void> deleteInterviewFeedback(@PathVariable Long id) {
        crud.delete(interviewFeedbackRepository, id, "InterviewFeedback");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/interview-scorecards")
    @Operation(summary = "GET all InterviewScorecard records")
    public List<InterviewScorecard> listInterviewScorecard() {
        return crud.findAll(interviewScorecardRepository);
    }

    @GetMapping("/interview-scorecards/{id}")
    @Operation(summary = "GET InterviewScorecard by id")
    public InterviewScorecard getInterviewScorecard(@PathVariable Long id) {
        return crud.get(interviewScorecardRepository, id, "InterviewScorecard");
    }

    @PostMapping("/interview-scorecards")
    @Operation(summary = "POST create InterviewScorecard")
    public ResponseEntity<InterviewScorecard> createInterviewScorecard(@Valid @RequestBody InterviewScorecard body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(interviewScorecardRepository, body));
    }

    @PutMapping("/interview-scorecards/{id}")
    @Operation(summary = "PUT replace InterviewScorecard")
    public InterviewScorecard replaceInterviewScorecard(@PathVariable Long id, @Valid @RequestBody InterviewScorecard body) {
        return crud.replace(interviewScorecardRepository, id, body, "InterviewScorecard");
    }

    @PatchMapping("/interview-scorecards/{id}")
    @Operation(summary = "PATCH InterviewScorecard")
    public InterviewScorecard patchInterviewScorecard(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(interviewScorecardRepository, id, updates, "InterviewScorecard");
    }

    @DeleteMapping("/interview-scorecards/{id}")
    @Operation(summary = "DELETE InterviewScorecard")
    public ResponseEntity<Void> deleteInterviewScorecard(@PathVariable Long id) {
        crud.delete(interviewScorecardRepository, id, "InterviewScorecard");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ai-interview-reports")
    @Operation(summary = "GET all AiInterviewReport records")
    public List<AiInterviewReport> listAiInterviewReport() {
        return crud.findAll(aiInterviewReportRepository);
    }

    @GetMapping("/ai-interview-reports/{id}")
    @Operation(summary = "GET AiInterviewReport by id")
    public AiInterviewReport getAiInterviewReport(@PathVariable Long id) {
        return crud.get(aiInterviewReportRepository, id, "AiInterviewReport");
    }

    @PostMapping("/ai-interview-reports")
    @Operation(summary = "POST create AiInterviewReport")
    public ResponseEntity<AiInterviewReport> createAiInterviewReport(@Valid @RequestBody AiInterviewReport body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(aiInterviewReportRepository, body));
    }

    @PutMapping("/ai-interview-reports/{id}")
    @Operation(summary = "PUT replace AiInterviewReport")
    public AiInterviewReport replaceAiInterviewReport(@PathVariable Long id, @Valid @RequestBody AiInterviewReport body) {
        return crud.replace(aiInterviewReportRepository, id, body, "AiInterviewReport");
    }

    @PatchMapping("/ai-interview-reports/{id}")
    @Operation(summary = "PATCH AiInterviewReport")
    public AiInterviewReport patchAiInterviewReport(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(aiInterviewReportRepository, id, updates, "AiInterviewReport");
    }

    @DeleteMapping("/ai-interview-reports/{id}")
    @Operation(summary = "DELETE AiInterviewReport")
    public ResponseEntity<Void> deleteAiInterviewReport(@PathVariable Long id) {
        crud.delete(aiInterviewReportRepository, id, "AiInterviewReport");
        return ResponseEntity.noContent().build();
    }

}
