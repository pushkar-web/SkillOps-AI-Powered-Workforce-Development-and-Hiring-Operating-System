package com.skillops.domain5_projects.entity;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_reviews", schema = "projects")
public class ProjectReview {

    public ProjectReview() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "submission_id", nullable = false)
    @NotNull
    private Long submissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id", insertable = false, updatable = false)
    @JsonIgnore
    private ProjectSubmission submission;

    @Column(name = "reviewer_id", nullable = false)
    private Long reviewerId;

    @Column(name = "technical_score")
    private Integer technicalScore;

    @Column(name = "design_score")
    private Integer designScore;

    @Column(name = "documentation_score")
    private Integer documentationScore;

    @Column(name = "innovation_score")
    private Integer innovationScore;

    @Column(name = "overall_score")
    private Integer overallScore;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "reviewed_at", nullable = false)
    private OffsetDateTime reviewedAt;

}
