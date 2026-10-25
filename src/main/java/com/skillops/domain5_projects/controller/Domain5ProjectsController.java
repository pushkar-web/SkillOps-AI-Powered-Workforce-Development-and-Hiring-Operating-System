package com.skillops.domain5_projects.controller;

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
import com.skillops.domain5_projects.entity.PortfolioItem;
import com.skillops.domain5_projects.entity.Project;
import com.skillops.domain5_projects.entity.ProjectMilestone;
import com.skillops.domain5_projects.entity.ProjectReview;
import com.skillops.domain5_projects.entity.ProjectSubmission;
import com.skillops.domain5_projects.repository.PortfolioItemRepository;
import com.skillops.domain5_projects.repository.ProjectMilestoneRepository;
import com.skillops.domain5_projects.repository.ProjectRepository;
import com.skillops.domain5_projects.repository.ProjectReviewRepository;
import com.skillops.domain5_projects.repository.ProjectSubmissionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/projects-domain")
@Tag(name = OpenApiConfig.DOMAIN_5)
public class Domain5ProjectsController {

    private final CrudFacade crud;
    private final ProjectRepository projectRepository;
    private final ProjectMilestoneRepository projectMilestoneRepository;
    private final ProjectSubmissionRepository projectSubmissionRepository;
    private final ProjectReviewRepository projectReviewRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public Domain5ProjectsController(
            
            CrudFacade crud,
            ProjectRepository projectRepository,
            ProjectMilestoneRepository projectMilestoneRepository,
            ProjectSubmissionRepository projectSubmissionRepository,
            ProjectReviewRepository projectReviewRepository,
            PortfolioItemRepository portfolioItemRepository
    ) {
        this.crud = crud;
        this.projectRepository = projectRepository;
        this.projectMilestoneRepository = projectMilestoneRepository;
        this.projectSubmissionRepository = projectSubmissionRepository;
        this.projectReviewRepository = projectReviewRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_5);
        body.put("projects", projectRepository.count());
        body.put("project-milestones", projectMilestoneRepository.count());
        body.put("project-submissions", projectSubmissionRepository.count());
        body.put("project-reviews", projectReviewRepository.count());
        body.put("portfolio-items", portfolioItemRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/projects")
    @Operation(summary = "GET all Project records")
    public List<Project> listProject() {
        return crud.findAll(projectRepository);
    }

    @GetMapping("/projects/{id}")
    @Operation(summary = "GET Project by id")
    public Project getProject(@PathVariable Long id) {
        return crud.get(projectRepository, id, "Project");
    }

    @PostMapping("/projects")
    @Operation(summary = "POST create Project")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(projectRepository, body));
    }

    @PutMapping("/projects/{id}")
    @Operation(summary = "PUT replace Project")
    public Project replaceProject(@PathVariable Long id, @Valid @RequestBody Project body) {
        return crud.replace(projectRepository, id, body, "Project");
    }

    @PatchMapping("/projects/{id}")
    @Operation(summary = "PATCH Project")
    public Project patchProject(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(projectRepository, id, updates, "Project");
    }

    @DeleteMapping("/projects/{id}")
    @Operation(summary = "DELETE Project")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        crud.delete(projectRepository, id, "Project");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project-milestones")
    @Operation(summary = "GET all ProjectMilestone records")
    public List<ProjectMilestone> listProjectMilestone() {
        return crud.findAll(projectMilestoneRepository);
    }

    @GetMapping("/project-milestones/{id}")
    @Operation(summary = "GET ProjectMilestone by id")
    public ProjectMilestone getProjectMilestone(@PathVariable Long id) {
        return crud.get(projectMilestoneRepository, id, "ProjectMilestone");
    }

    @PostMapping("/project-milestones")
    @Operation(summary = "POST create ProjectMilestone")
    public ResponseEntity<ProjectMilestone> createProjectMilestone(@Valid @RequestBody ProjectMilestone body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(projectMilestoneRepository, body));
    }

    @PutMapping("/project-milestones/{id}")
    @Operation(summary = "PUT replace ProjectMilestone")
    public ProjectMilestone replaceProjectMilestone(@PathVariable Long id, @Valid @RequestBody ProjectMilestone body) {
        return crud.replace(projectMilestoneRepository, id, body, "ProjectMilestone");
    }

    @PatchMapping("/project-milestones/{id}")
    @Operation(summary = "PATCH ProjectMilestone")
    public ProjectMilestone patchProjectMilestone(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(projectMilestoneRepository, id, updates, "ProjectMilestone");
    }

    @DeleteMapping("/project-milestones/{id}")
    @Operation(summary = "DELETE ProjectMilestone")
    public ResponseEntity<Void> deleteProjectMilestone(@PathVariable Long id) {
        crud.delete(projectMilestoneRepository, id, "ProjectMilestone");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project-submissions")
    @Operation(summary = "GET all ProjectSubmission records")
    public List<ProjectSubmission> listProjectSubmission() {
        return crud.findAll(projectSubmissionRepository);
    }

    @GetMapping("/project-submissions/{id}")
    @Operation(summary = "GET ProjectSubmission by id")
    public ProjectSubmission getProjectSubmission(@PathVariable Long id) {
        return crud.get(projectSubmissionRepository, id, "ProjectSubmission");
    }

    @PostMapping("/project-submissions")
    @Operation(summary = "POST create ProjectSubmission")
    public ResponseEntity<ProjectSubmission> createProjectSubmission(@Valid @RequestBody ProjectSubmission body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(projectSubmissionRepository, body));
    }

    @PutMapping("/project-submissions/{id}")
    @Operation(summary = "PUT replace ProjectSubmission")
    public ProjectSubmission replaceProjectSubmission(@PathVariable Long id, @Valid @RequestBody ProjectSubmission body) {
        return crud.replace(projectSubmissionRepository, id, body, "ProjectSubmission");
    }

    @PatchMapping("/project-submissions/{id}")
    @Operation(summary = "PATCH ProjectSubmission")
    public ProjectSubmission patchProjectSubmission(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(projectSubmissionRepository, id, updates, "ProjectSubmission");
    }

    @DeleteMapping("/project-submissions/{id}")
    @Operation(summary = "DELETE ProjectSubmission")
    public ResponseEntity<Void> deleteProjectSubmission(@PathVariable Long id) {
        crud.delete(projectSubmissionRepository, id, "ProjectSubmission");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project-reviews")
    @Operation(summary = "GET all ProjectReview records")
    public List<ProjectReview> listProjectReview() {
        return crud.findAll(projectReviewRepository);
    }

    @GetMapping("/project-reviews/{id}")
    @Operation(summary = "GET ProjectReview by id")
    public ProjectReview getProjectReview(@PathVariable Long id) {
        return crud.get(projectReviewRepository, id, "ProjectReview");
    }

    @PostMapping("/project-reviews")
    @Operation(summary = "POST create ProjectReview")
    public ResponseEntity<ProjectReview> createProjectReview(@Valid @RequestBody ProjectReview body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(projectReviewRepository, body));
    }

    @PutMapping("/project-reviews/{id}")
    @Operation(summary = "PUT replace ProjectReview")
    public ProjectReview replaceProjectReview(@PathVariable Long id, @Valid @RequestBody ProjectReview body) {
        return crud.replace(projectReviewRepository, id, body, "ProjectReview");
    }

    @PatchMapping("/project-reviews/{id}")
    @Operation(summary = "PATCH ProjectReview")
    public ProjectReview patchProjectReview(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(projectReviewRepository, id, updates, "ProjectReview");
    }

    @DeleteMapping("/project-reviews/{id}")
    @Operation(summary = "DELETE ProjectReview")
    public ResponseEntity<Void> deleteProjectReview(@PathVariable Long id) {
        crud.delete(projectReviewRepository, id, "ProjectReview");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/portfolio-items")
    @Operation(summary = "GET all PortfolioItem records")
    public List<PortfolioItem> listPortfolioItem() {
        return crud.findAll(portfolioItemRepository);
    }

    @GetMapping("/portfolio-items/{id}")
    @Operation(summary = "GET PortfolioItem by id")
    public PortfolioItem getPortfolioItem(@PathVariable Long id) {
        return crud.get(portfolioItemRepository, id, "PortfolioItem");
    }

    @PostMapping("/portfolio-items")
    @Operation(summary = "POST create PortfolioItem")
    public ResponseEntity<PortfolioItem> createPortfolioItem(@Valid @RequestBody PortfolioItem body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(portfolioItemRepository, body));
    }

    @PutMapping("/portfolio-items/{id}")
    @Operation(summary = "PUT replace PortfolioItem")
    public PortfolioItem replacePortfolioItem(@PathVariable Long id, @Valid @RequestBody PortfolioItem body) {
        return crud.replace(portfolioItemRepository, id, body, "PortfolioItem");
    }

    @PatchMapping("/portfolio-items/{id}")
    @Operation(summary = "PATCH PortfolioItem")
    public PortfolioItem patchPortfolioItem(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(portfolioItemRepository, id, updates, "PortfolioItem");
    }

    @DeleteMapping("/portfolio-items/{id}")
    @Operation(summary = "DELETE PortfolioItem")
    public ResponseEntity<Void> deletePortfolioItem(@PathVariable Long id) {
        crud.delete(portfolioItemRepository, id, "PortfolioItem");
        return ResponseEntity.noContent().build();
    }

}
