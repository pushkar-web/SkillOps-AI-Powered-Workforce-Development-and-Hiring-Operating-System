package com.skillops.domain8_recruitment.controller;

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
import com.skillops.domain8_recruitment.entity.CandidateMatch;
import com.skillops.domain8_recruitment.entity.Company;
import com.skillops.domain8_recruitment.entity.HiringStatus;
import com.skillops.domain8_recruitment.entity.Job;
import com.skillops.domain8_recruitment.entity.JobApplication;
import com.skillops.domain8_recruitment.entity.JobRequirement;
import com.skillops.domain8_recruitment.entity.Offer;
import com.skillops.domain8_recruitment.repository.CandidateMatchRepository;
import com.skillops.domain8_recruitment.repository.CompanyRepository;
import com.skillops.domain8_recruitment.repository.HiringStatusRepository;
import com.skillops.domain8_recruitment.repository.JobApplicationRepository;
import com.skillops.domain8_recruitment.repository.JobRepository;
import com.skillops.domain8_recruitment.repository.JobRequirementRepository;
import com.skillops.domain8_recruitment.repository.OfferRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/recruitment-domain")
@Tag(name = OpenApiConfig.DOMAIN_8)
public class Domain8RecruitmentController {

    private final CrudFacade crud;
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final JobRequirementRepository jobRequirementRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final CandidateMatchRepository candidateMatchRepository;
    private final OfferRepository offerRepository;
    private final HiringStatusRepository hiringStatusRepository;

    public Domain8RecruitmentController(
            CrudFacade crud,
            CompanyRepository companyRepository,
            JobRepository jobRepository,
            JobRequirementRepository jobRequirementRepository,
            JobApplicationRepository jobApplicationRepository,
            CandidateMatchRepository candidateMatchRepository,
            OfferRepository offerRepository,
            HiringStatusRepository hiringStatusRepository
    ) {
        this.crud = crud;
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.jobRequirementRepository = jobRequirementRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.candidateMatchRepository = candidateMatchRepository;
        this.offerRepository = offerRepository;
        this.hiringStatusRepository = hiringStatusRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_8);
        body.put("companies", companyRepository.count());
        body.put("jobs", jobRepository.count());
        body.put("job-requirements", jobRequirementRepository.count());
        body.put("applications", jobApplicationRepository.count());
        body.put("candidate-matches", candidateMatchRepository.count());
        body.put("offers", offerRepository.count());
        body.put("hiring-status", hiringStatusRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/companies")
    @Operation(summary = "GET all Company records")
    public List<Company> listCompany() {
        return crud.findAll(companyRepository);
    }

    @GetMapping("/companies/{id}")
    @Operation(summary = "GET Company by id")
    public Company getCompany(@PathVariable Long id) {
        return crud.get(companyRepository, id, "Company");
    }

    @PostMapping("/companies")
    @Operation(summary = "POST create Company")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(companyRepository, body));
    }

    @PutMapping("/companies/{id}")
    @Operation(summary = "PUT replace Company")
    public Company replaceCompany(@PathVariable Long id, @Valid @RequestBody Company body) {
        return crud.replace(companyRepository, id, body, "Company");
    }

    @PatchMapping("/companies/{id}")
    @Operation(summary = "PATCH Company")
    public Company patchCompany(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(companyRepository, id, updates, "Company");
    }

    @DeleteMapping("/companies/{id}")
    @Operation(summary = "DELETE Company")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        crud.delete(companyRepository, id, "Company");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jobs")
    @Operation(summary = "GET all Job records")
    public List<Job> listJob() {
        return crud.findAll(jobRepository);
    }

    @GetMapping("/jobs/{id}")
    @Operation(summary = "GET Job by id")
    public Job getJob(@PathVariable Long id) {
        return crud.get(jobRepository, id, "Job");
    }

    @PostMapping("/jobs")
    @Operation(summary = "POST create Job")
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(jobRepository, body));
    }

    @PutMapping("/jobs/{id}")
    @Operation(summary = "PUT replace Job")
    public Job replaceJob(@PathVariable Long id, @Valid @RequestBody Job body) {
        return crud.replace(jobRepository, id, body, "Job");
    }

    @PatchMapping("/jobs/{id}")
    @Operation(summary = "PATCH Job")
    public Job patchJob(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(jobRepository, id, updates, "Job");
    }

    @DeleteMapping("/jobs/{id}")
    @Operation(summary = "DELETE Job")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        crud.delete(jobRepository, id, "Job");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/job-requirements")
    @Operation(summary = "GET all JobRequirement records")
    public List<JobRequirement> listJobRequirement() {
        return crud.findAll(jobRequirementRepository);
    }

    @GetMapping("/job-requirements/{id}")
    @Operation(summary = "GET JobRequirement by id")
    public JobRequirement getJobRequirement(@PathVariable Long id) {
        return crud.get(jobRequirementRepository, id, "JobRequirement");
    }

    @PostMapping("/job-requirements")
    @Operation(summary = "POST create JobRequirement")
    public ResponseEntity<JobRequirement> createJobRequirement(@Valid @RequestBody JobRequirement body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(jobRequirementRepository, body));
    }

    @PutMapping("/job-requirements/{id}")
    @Operation(summary = "PUT replace JobRequirement")
    public JobRequirement replaceJobRequirement(@PathVariable Long id, @Valid @RequestBody JobRequirement body) {
        return crud.replace(jobRequirementRepository, id, body, "JobRequirement");
    }

    @PatchMapping("/job-requirements/{id}")
    @Operation(summary = "PATCH JobRequirement")
    public JobRequirement patchJobRequirement(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(jobRequirementRepository, id, updates, "JobRequirement");
    }

    @DeleteMapping("/job-requirements/{id}")
    @Operation(summary = "DELETE JobRequirement")
    public ResponseEntity<Void> deleteJobRequirement(@PathVariable Long id) {
        crud.delete(jobRequirementRepository, id, "JobRequirement");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/applications")
    @Operation(summary = "GET all JobApplication records")
    public List<JobApplication> listJobApplication() {
        return crud.findAll(jobApplicationRepository);
    }

    @GetMapping("/applications/{id}")
    @Operation(summary = "GET JobApplication by id")
    public JobApplication getJobApplication(@PathVariable Long id) {
        return crud.get(jobApplicationRepository, id, "JobApplication");
    }

    @PostMapping("/applications")
    @Operation(summary = "POST create JobApplication")
    public ResponseEntity<JobApplication> createJobApplication(@Valid @RequestBody JobApplication body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(jobApplicationRepository, body));
    }

    @PutMapping("/applications/{id}")
    @Operation(summary = "PUT replace JobApplication")
    public JobApplication replaceJobApplication(@PathVariable Long id, @Valid @RequestBody JobApplication body) {
        return crud.replace(jobApplicationRepository, id, body, "JobApplication");
    }

    @PatchMapping("/applications/{id}")
    @Operation(summary = "PATCH JobApplication")
    public JobApplication patchJobApplication(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(jobApplicationRepository, id, updates, "JobApplication");
    }

    @DeleteMapping("/applications/{id}")
    @Operation(summary = "DELETE JobApplication")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        crud.delete(jobApplicationRepository, id, "JobApplication");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/candidate-matches")
    @Operation(summary = "GET all CandidateMatch records")
    public List<CandidateMatch> listCandidateMatch() {
        return crud.findAll(candidateMatchRepository);
    }

    @GetMapping("/candidate-matches/{id}")
    @Operation(summary = "GET CandidateMatch by id")
    public CandidateMatch getCandidateMatch(@PathVariable Long id) {
        return crud.get(candidateMatchRepository, id, "CandidateMatch");
    }

    @PostMapping("/candidate-matches")
    @Operation(summary = "POST create CandidateMatch")
    public ResponseEntity<CandidateMatch> createCandidateMatch(@Valid @RequestBody CandidateMatch body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(candidateMatchRepository, body));
    }

    @PutMapping("/candidate-matches/{id}")
    @Operation(summary = "PUT replace CandidateMatch")
    public CandidateMatch replaceCandidateMatch(@PathVariable Long id, @Valid @RequestBody CandidateMatch body) {
        return crud.replace(candidateMatchRepository, id, body, "CandidateMatch");
    }

    @PatchMapping("/candidate-matches/{id}")
    @Operation(summary = "PATCH CandidateMatch")
    public CandidateMatch patchCandidateMatch(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(candidateMatchRepository, id, updates, "CandidateMatch");
    }

    @DeleteMapping("/candidate-matches/{id}")
    @Operation(summary = "DELETE CandidateMatch")
    public ResponseEntity<Void> deleteCandidateMatch(@PathVariable Long id) {
        crud.delete(candidateMatchRepository, id, "CandidateMatch");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/offers")
    @Operation(summary = "GET all Offer records")
    public List<Offer> listOffer() {
        return crud.findAll(offerRepository);
    }

    @GetMapping("/offers/{id}")
    @Operation(summary = "GET Offer by id")
    public Offer getOffer(@PathVariable Long id) {
        return crud.get(offerRepository, id, "Offer");
    }

    @PostMapping("/offers")
    @Operation(summary = "POST create Offer")
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(offerRepository, body));
    }

    @PutMapping("/offers/{id}")
    @Operation(summary = "PUT replace Offer")
    public Offer replaceOffer(@PathVariable Long id, @Valid @RequestBody Offer body) {
        return crud.replace(offerRepository, id, body, "Offer");
    }

    @PatchMapping("/offers/{id}")
    @Operation(summary = "PATCH Offer")
    public Offer patchOffer(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(offerRepository, id, updates, "Offer");
    }

    @DeleteMapping("/offers/{id}")
    @Operation(summary = "DELETE Offer")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        crud.delete(offerRepository, id, "Offer");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hiring-status")
    @Operation(summary = "GET all HiringStatus records")
    public List<HiringStatus> listHiringStatus() {
        return crud.findAll(hiringStatusRepository);
    }

    @GetMapping("/hiring-status/{id}")
    @Operation(summary = "GET HiringStatus by id")
    public HiringStatus getHiringStatus(@PathVariable Long id) {
        return crud.get(hiringStatusRepository, id, "HiringStatus");
    }

    @PostMapping("/hiring-status")
    @Operation(summary = "POST create HiringStatus")
    public ResponseEntity<HiringStatus> createHiringStatus(@Valid @RequestBody HiringStatus body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(hiringStatusRepository, body));
    }

    @PutMapping("/hiring-status/{id}")
    @Operation(summary = "PUT replace HiringStatus")
    public HiringStatus replaceHiringStatus(@PathVariable Long id, @Valid @RequestBody HiringStatus body) {
        return crud.replace(hiringStatusRepository, id, body, "HiringStatus");
    }

    @PatchMapping("/hiring-status/{id}")
    @Operation(summary = "PATCH HiringStatus")
    public HiringStatus patchHiringStatus(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(hiringStatusRepository, id, updates, "HiringStatus");
    }

    @DeleteMapping("/hiring-status/{id}")
    @Operation(summary = "DELETE HiringStatus")
    public ResponseEntity<Void> deleteHiringStatus(@PathVariable Long id) {
        crud.delete(hiringStatusRepository, id, "HiringStatus");
        return ResponseEntity.noContent().build();
    }

}
