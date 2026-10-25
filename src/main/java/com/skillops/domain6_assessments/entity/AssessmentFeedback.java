package com.skillops.domain6_assessments.entity;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assessment_feedback", schema = "assessments")
public class AssessmentFeedback {

    public AssessmentFeedback() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "evaluation_result_id", nullable = false)
    private Long evaluationResultId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_result_id", insertable = false, updatable = false)
    @JsonIgnore
    private EvaluationResult evaluationResult;

    @Column(name = "strengths")
    private String strengths;

    @Column(name = "weaknesses")
    private String weaknesses;

    @Column(name = "recommendations")
    private String recommendations;

    @Column(name = "generated_at", nullable = false)
    private OffsetDateTime generatedAt;

}
