package com.skillops.domain10_analytics.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnalyticsResponseBean {
    private Long id;
    private Long dashboardId;
    private Long profileId;
    private Integer learningScore;
    private Integer skillScore;
    private Integer assessmentScore;
    private Integer projectScore;
    private Integer interviewScore;
    private Integer employabilityScore;
    private Integer profileCompletion;
    private Integer totalCourses;
    private Integer completedCourses;
    private Integer totalProjects;
    private Integer completedProjects;
    private Integer certificatesEarned;
    private Integer applicationsSubmitted;
    private Integer interviewsCompleted;
    private Integer offersReceived;
    private Integer streakDays;
    private OffsetDateTime updatedAt;
}
