package com.skillops.domain6_assessments.entity;

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
@Table(name = "coding_submissions", schema = "assessments")
public class CodingSubmission {

    public CodingSubmission() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "challenge_id", nullable = false)
    private Long challengeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", insertable = false, updatable = false)
    @JsonIgnore
    private CodingChallenge challenge;

    @NotNull
    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "repository_url")
    private String repositoryUrl;

    @Column(name = "source_code_url")
    private String sourceCodeUrl;

    @Column(name = "execution_score")
    private Integer executionScore;

    @Column(name = "quality_score")
    private Integer qualityScore;

    @Column(name = "submitted_at", nullable = false)
    private OffsetDateTime submittedAt;

}
