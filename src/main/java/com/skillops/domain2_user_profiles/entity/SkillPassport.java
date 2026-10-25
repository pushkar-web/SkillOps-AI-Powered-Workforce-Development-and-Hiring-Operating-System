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
@Table(name = "skill_passports", schema = "user_profiles")
public class SkillPassport {

    public SkillPassport() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false, unique = true)
    @NotNull
    private Long profileId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @JsonIgnore
    private Profile profile;

    @Column(name = "passport_score", nullable = false)
    private BigDecimal passportScore;

    @Column(name = "total_skills", nullable = false)
    private Integer totalSkills;

    @Column(name = "verified_skills", nullable = false)
    private Integer verifiedSkills;

    @Column(name = "completed_courses", nullable = false)
    private Integer completedCourses;

    @Column(name = "completed_projects", nullable = false)
    private Integer completedProjects;

    @Column(name = "certificates_earned", nullable = false)
    private Integer certificatesEarned;

    @Column(name = "verification_status", nullable = false)
    @NotBlank
    private String verificationStatus;

    @Column(name = "last_updated", nullable = false)
    private OffsetDateTime lastUpdated;

}
