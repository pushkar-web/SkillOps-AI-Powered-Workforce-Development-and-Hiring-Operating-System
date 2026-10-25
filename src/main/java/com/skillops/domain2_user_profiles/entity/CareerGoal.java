package com.skillops.domain2_user_profiles.entity;

import java.math.BigDecimal;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "career_goals", schema = "user_profiles")
public class CareerGoal {

    public CareerGoal() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    @NotNull
    private Long profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @JsonIgnore
    private Profile profile;

    @Column(name = "target_role", nullable = false)
    @NotBlank
    private String targetRole;

    @Column(name = "target_industry")
    private String targetIndustry;

    @Column(name = "target_company")
    private String targetCompany;

    @Column(name = "preferred_location")
    private String preferredLocation;

    @Column(name = "expected_ctc")
    private BigDecimal expectedCtc;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "target_timeline_months")
    private Integer targetTimelineMonths;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

}
