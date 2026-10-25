package com.skillops.domain7_interviews.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interview_rounds", schema = "interviews")
public class InterviewRound {

    public InterviewRound() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "interview_id", nullable = false)
    private Long interviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", insertable = false, updatable = false)
    @JsonIgnore
    private Interview interview;

    @NotBlank
    @Column(name = "round_name", nullable = false)
    private String roundName;

    @Column(name = "round_type")
    private String roundType;

    @NotNull
    @Column(name = "round_number", nullable = false)
    private Integer roundNumber;

    @Column(name = "interviewer")
    private String interviewer;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;


    @OneToMany(mappedBy = "round", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InterviewFeedback> feedbacks = new ArrayList<>();
}
