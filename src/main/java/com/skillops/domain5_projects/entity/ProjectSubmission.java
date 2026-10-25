package com.skillops.domain5_projects.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_submissions", schema = "projects")
public class ProjectSubmission {

    public ProjectSubmission() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    @NotNull
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    @JsonIgnore
    private Project project;

    @Column(name = "profile_id", nullable = false)
    @NotNull
    private Long profileId;

    @Column(name = "repository_url")
    private String repositoryUrl;

    @Column(name = "live_demo_url")
    private String liveDemoUrl;

    @Column(name = "documentation_url")
    private String documentationUrl;

    @Column(name = "submission_status", nullable = false)
    private String submissionStatus;

    @Column(name = "submitted_at", nullable = false)
    private OffsetDateTime submittedAt;


    @OneToMany(mappedBy = "submission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectReview> reviews = new ArrayList<>();
}
