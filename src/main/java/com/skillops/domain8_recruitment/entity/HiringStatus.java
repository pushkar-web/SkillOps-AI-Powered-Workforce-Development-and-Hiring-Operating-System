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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hiring_status", schema = "recruitment")
public class HiringStatus {

    public HiringStatus() {
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

    @NotBlank
    @Column(name = "current_stage", nullable = false)
    private String currentStage;

    @Column(name = "background_verification", nullable = false)
    private Boolean backgroundVerification;

    @Column(name = "documents_verified", nullable = false)
    private Boolean documentsVerified;

    @Column(name = "onboarding_completed", nullable = false)
    private Boolean onboardingCompleted;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

}
