package com.skillops.domain8_recruitment.entity;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "applications", schema = "recruitment")
public class JobApplication {

    public JobApplication() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "job_id", nullable = false)
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    @JsonIgnore
    private Job job;

    @NotNull
    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "applied_on", nullable = false)
    private LocalDate appliedOn;

    @NotBlank
    @Column(name = "application_status", nullable = false)
    private String applicationStatus;

    @Column(name = "ai_match_score")
    private Integer aiMatchScore;

    @Column(name = "shortlisted", nullable = false)
    private Boolean shortlisted;


    @OneToOne(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private CandidateMatch candidateMatch;

    @OneToOne(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Offer offer;

    @OneToOne(mappedBy = "application", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private HiringStatus hiringStatus;
}
