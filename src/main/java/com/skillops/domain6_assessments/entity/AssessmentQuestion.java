package com.skillops.domain6_assessments.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assessment_questions", schema = "assessments")
public class AssessmentQuestion {

    public AssessmentQuestion() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "assessment_id", nullable = false)
    private Long assessmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id", insertable = false, updatable = false)
    @JsonIgnore
    private Assessment assessment;

    @NotBlank
    @Column(name = "question_type", nullable = false)
    private String questionType;

    @NotBlank
    @Column(name = "question", nullable = false)
    private String question;

    @NotNull
    @Column(name = "marks", nullable = false)
    private Integer marks;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

}
