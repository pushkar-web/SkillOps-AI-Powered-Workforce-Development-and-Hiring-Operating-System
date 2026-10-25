package com.skillops.domain3_skills.entity;

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
@Table(name = "skill_recommendations", schema = "skills")
public class SkillRecommendation {

    public SkillRecommendation() {
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

    @Column(name = "recommendation_reason")
    private String recommendationReason;

    @Column(name = "recommendation_score")
    private Integer recommendationScore;

    @Column(name = "recommendation_type")
    private String recommendationType;

    @Column(name = "generated_at", nullable = false)
    private OffsetDateTime generatedAt;

}
