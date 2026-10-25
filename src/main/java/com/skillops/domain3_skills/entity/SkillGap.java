package com.skillops.domain3_skills.entity;

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
@Table(name = "skill_gaps", schema = "skills")
public class SkillGap {

    public SkillGap() {
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

    @Column(name = "current_level", nullable = false)
    private Integer currentLevel;

    @Column(name = "target_level", nullable = false)
    private Integer targetLevel;

    @Column(name = "gap_score", nullable = false)
    private Integer gapScore;

    @Column(name = "priority")
    private String priority;

}
