package com.skillops.domain7_interviews.entity;

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
@Table(name = "interview_feedback", schema = "interviews")
public class InterviewFeedback {

    public InterviewFeedback() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "round_id", nullable = false)
    private Long roundId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id", insertable = false, updatable = false)
    @JsonIgnore
    private InterviewRound round;

    @Column(name = "reviewer_id")
    private Long reviewerId;

    @Column(name = "communication_rating")
    private Integer communicationRating;

    @Column(name = "technical_rating")
    private Integer technicalRating;

    @Column(name = "problem_solving_rating")
    private Integer problemSolvingRating;

    @Column(name = "confidence_rating")
    private Integer confidenceRating;

    @Column(name = "strengths")
    private String strengths;

    @Column(name = "improvements")
    private String improvements;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "submitted_at", nullable = false)
    private OffsetDateTime submittedAt;

}
