package com.skillops.domain6_assessments.entity;

import java.time.LocalDate;
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
@Table(name = "certificates", schema = "assessments")
public class AssessmentCertificate {

    public AssessmentCertificate() {
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

    @NotNull
    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @NotBlank
    @Column(name = "certificate_number", nullable = false, unique = true)
    private String certificateNumber;

    @Column(name = "certificate_url")
    private String certificateUrl;

    @Column(name = "issued_on")
    private LocalDate issuedOn;

    @Column(name = "verification_hash")
    private String verificationHash;

}
