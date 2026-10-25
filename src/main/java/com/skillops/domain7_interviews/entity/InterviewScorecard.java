package com.skillops.domain7_interviews.entity;

import java.time.OffsetDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interview_scorecards", schema = "interviews")
public class InterviewScorecard {

    public InterviewScorecard() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "interview_id", nullable = false, unique = true)
    private Long interviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", insertable = false, updatable = false)
    @JsonIgnore
    private Interview interview;

    @Column(name = "communication_score")
    private Integer communicationScore;

    @Column(name = "technical_score")
    private Integer technicalScore;

    @Column(name = "aptitude_score")
    private Integer aptitudeScore;

    @Column(name = "behavioral_score")
    private Integer behavioralScore;

    @Column(name = "coding_score")
    private Integer codingScore;

    @Column(name = "final_score")
    private Integer finalScore;

    @Column(name = "recommendation")
    private String recommendation;

    @Column(name = "generated_at", nullable = false)
    private OffsetDateTime generatedAt;


    @OneToOne(mappedBy = "scorecard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private AiInterviewReport aiReport;
}
