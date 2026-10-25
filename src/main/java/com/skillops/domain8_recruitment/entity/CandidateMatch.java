package com.skillops.domain8_recruitment.entity;

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
@Table(name = "candidate_matches", schema = "recruitment")
public class CandidateMatch {

    public CandidateMatch() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "application_id", nullable = false, unique = true)
    private Long applicationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", insertable = false, updatable = false)
    @JsonIgnore
    private JobApplication application;

    @Column(name = "skill_score")
    private Integer skillScore;

    @Column(name = "project_score")
    private Integer projectScore;

    @Column(name = "assessment_score")
    private Integer assessmentScore;

    @Column(name = "interview_score")
    private Integer interviewScore;

    @Column(name = "overall_score")
    private Integer overallScore;

    @Column(name = "recommendation")
    private String recommendation;

    @Column(name = "generated_at", nullable = false)
    private OffsetDateTime generatedAt;

}
