package com.skillops.domain3_skills.entity;

import java.time.LocalDate;
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
@Table(name = "user_skills", schema = "skills")
public class UserSkill {

    public UserSkill() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_id", nullable = false)
    @NotNull
    private Long profileId;

    @Column(name = "skill_id", nullable = false)
    @NotNull
    private Long skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    @JsonIgnore
    private Skill skill;

    @Column(name = "competency_level_id")
    private Long competencyLevelId;

    @Column(name = "skill_assessment_id")
    private Long skillAssessmentId;

    @Column(name = "proficiency_score", nullable = false)
    private Integer proficiencyScore;

    @Column(name = "verified", nullable = false)
    private Boolean verified;

    @Column(name = "last_used")
    private LocalDate lastUsed;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

}
