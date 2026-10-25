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
@Table(name = "competency_levels", schema = "skills")
public class CompetencyLevel {

    public CompetencyLevel() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "competency_id", nullable = false)
    @NotNull
    private Long competencyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competency_id", insertable = false, updatable = false)
    @JsonIgnore
    private Competency competency;

    @Column(name = "level_name", nullable = false)
    private String levelName;

    @Column(name = "level_value", nullable = false)
    private Integer levelValue;

    @Column(name = "criteria")
    private String criteria;

}
