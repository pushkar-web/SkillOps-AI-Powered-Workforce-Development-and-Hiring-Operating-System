package com.skillops.domain8_recruitment.entity;

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
@Table(name = "job_requirements", schema = "recruitment")
public class JobRequirement {

    public JobRequirement() {
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

    @NotBlank
    @Column(name = "requirement_type", nullable = false)
    private String requirementType;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "mandatory", nullable = false)
    private Boolean mandatory;

    @Column(name = "priority")
    private Integer priority;

}
