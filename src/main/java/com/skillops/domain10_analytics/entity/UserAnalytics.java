package com.skillops.domain10_analytics.entity;

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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_analytics", schema = "analytics")
public class UserAnalytics {

    public UserAnalytics() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "dashboard_id", nullable = false)
    private Long dashboardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dashboard_id", insertable = false, updatable = false)
    @JsonIgnore
    private Dashboard dashboard;

    @NotNull
    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @Column(name = "learning_score")
    private Integer learningScore;

    @Column(name = "skill_score")
    private Integer skillScore;

    @Column(name = "assessment_score")
    private Integer assessmentScore;

    @Column(name = "project_score")
    private Integer projectScore;

    @Column(name = "interview_score")
    private Integer interviewScore;

    @Column(name = "employability_score")
    private Integer employabilityScore;

    @Column(name = "profile_completion")
    private Integer profileCompletion;

    @Column(name = "total_courses")
    private Integer totalCourses;

    @Column(name = "completed_courses")
    private Integer completedCourses;

    @Column(name = "total_projects")
    private Integer totalProjects;

    @Column(name = "completed_projects")
    private Integer completedProjects;

    @Column(name = "certificates_earned")
    private Integer certificatesEarned;

    @Column(name = "applications_submitted")
    private Integer applicationsSubmitted;

    @Column(name = "interviews_completed")
    private Integer interviewsCompleted;

    @Column(name = "offers_received")
    private Integer offersReceived;

    @Column(name = "streak_days")
    private Integer streakDays;

    @Column(name = "current_rank")
    private String currentRank;

    @Column(name = "recommendation")
    private String recommendation;

    @Column(name = "calculated_at", nullable = false)
    private OffsetDateTime calculatedAt;

}
