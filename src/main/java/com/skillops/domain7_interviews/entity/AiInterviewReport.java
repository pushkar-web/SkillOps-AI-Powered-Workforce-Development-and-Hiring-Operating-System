package com.skillops.domain7_interviews.entity;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ai_interview_reports", schema = "interviews")
public class AiInterviewReport {

    public AiInterviewReport() {
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

    @Column(name = "scorecard_id")
    private Long scorecardId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scorecard_id", insertable = false, updatable = false)
    @JsonIgnore
    private InterviewScorecard scorecard;

    @Column(name = "confidence_score")
    private BigDecimal confidenceScore;

    @Column(name = "strengths")
    private String strengths;

    @Column(name = "weaknesses")
    private String weaknesses;

    @Column(name = "improvement_areas")
    private String improvementAreas;

    @Column(name = "suggested_learning_path")
    private String suggestedLearningPath;

    @Column(name = "ai_summary")
    private String aiSummary;

    @Column(name = "generated_at", nullable = false)
    private OffsetDateTime generatedAt;

}
