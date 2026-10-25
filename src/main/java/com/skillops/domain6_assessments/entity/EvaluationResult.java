package com.skillops.domain6_assessments.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "evaluation_results", schema = "assessments")
public class EvaluationResult {

    public EvaluationResult() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attempt_id")
    private Long attemptId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", insertable = false, updatable = false)
    @JsonIgnore
    private AssessmentAttempt attempt;

    @Column(name = "coding_submission_id")
    private Long codingSubmissionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coding_submission_id", insertable = false, updatable = false)
    @JsonIgnore
    private CodingSubmission codingSubmission;

    @Column(name = "objective_score")
    private Integer objectiveScore;

    @Column(name = "coding_score")
    private Integer codingScore;

    @Column(name = "ai_score")
    private Integer aiScore;

    @Column(name = "final_score")
    private Integer finalScore;

    @Column(name = "remarks")
    private String remarks;


    @OneToOne(mappedBy = "evaluationResult", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private AssessmentFeedback feedback;
}
