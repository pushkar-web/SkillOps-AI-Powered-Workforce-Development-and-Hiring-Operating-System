import os

target_dir = os.path.join("src", "main", "java", "com", "skillops", "response")
os.makedirs(target_dir, exist_ok=True)

beans = {
    "ApiResponse.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private int status;
    private String message;
    private T data;
    private String path;
    @Builder.Default
    private OffsetDateTime timestamp = OffsetDateTime.now();

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(200)
                .message("Operation completed successfully")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(int status, String message, String path) {
        return ApiResponse.<T>builder()
                .success(false)
                .status(status)
                .message(message)
                .path(path)
                .build();
    }
}
""",

    "PaginatedResponse.java": """package com.skillops.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
}
""",

    "ErrorResponse.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> fieldErrors;
    @Builder.Default
    private OffsetDateTime timestamp = OffsetDateTime.now();
}
""",

    "HealthResponse.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthResponse {
    private String status;
    private String service;
    private String version;
    private OffsetDateTime timestamp;
}
""",

    "DomainSummaryResponse.java": """package com.skillops.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainSummaryResponse {
    private String domainName;
    private int domainCode;
    private Map<String, Long> metrics;
    private String status;
}
""",

    "UserResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseBean {
    private Long id;
    private Long tenantId;
    private String email;
    private String phone;
    private String status;
    private Boolean emailVerified;
    private OffsetDateTime lastLogin;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
""",

    "RoleResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseBean {
    private Long id;
    private String name;
    private String description;
    private OffsetDateTime createdAt;
}
""",

    "SessionResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseBean {
    private Long id;
    private Long userId;
    private String ipAddress;
    private String device;
    private OffsetDateTime expiresAt;
}
""",

    "AuditLogResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponseBean {
    private Long id;
    private Long userId;
    private String action;
    private String entityName;
    private Long entityId;
    private String oldValue;
    private String newValue;
    private OffsetDateTime timestamp;
}
""",

    "ProfileResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseBean {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String bio;
    private String profilePicture;
    private String city;
    private String state;
    private String country;
    private String linkedinUrl;
    private String githubUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
""",

    "ResumeResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponseBean {
    private Long id;
    private Long profileId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Integer version;
    private Boolean isPrimary;
    private OffsetDateTime uploadedAt;
}
""",

    "EducationResponseBean.java": """package com.skillops.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseBean {
    private Long id;
    private Long profileId;
    private String instituteName;
    private String degree;
    private String specialization;
    private BigDecimal cgpa;
    private Integer passingYear;
    private Integer startYear;
    private Integer endYear;
    private Boolean isCurrent;
}
""",

    "ExperienceResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResponseBean {
    private Long id;
    private Long profileId;
    private String companyName;
    private String designation;
    private String employmentType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean currentlyWorking;
    private String location;
    private String responsibilities;
}
""",

    "SkillPassportResponseBean.java": """package com.skillops.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillPassportResponseBean {
    private Long id;
    private Long profileId;
    private BigDecimal passportScore;
    private Integer totalSkills;
    private Integer verifiedSkills;
    private Integer completedCourses;
    private Integer completedProjects;
    private Integer certificatesEarned;
    private String verificationStatus;
    private OffsetDateTime lastUpdated;
}
""",

    "SkillResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillResponseBean {
    private Long id;
    private Long categoryId;
    private String skillName;
    private String description;
    private String difficultyLevel;
    private Boolean isVerified;
    private OffsetDateTime createdAt;
}
""",

    "SkillCategoryResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillCategoryResponseBean {
    private Long id;
    private String categoryName;
    private String description;
    private String icon;
    private Boolean isActive;
    private OffsetDateTime createdAt;
}
""",

    "UserSkillResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSkillResponseBean {
    private Long id;
    private Long profileId;
    private Long skillId;
    private Long competencyLevelId;
    private Long skillAssessmentId;
    private Integer proficiencyScore;
    private Boolean verified;
    private LocalDate lastUsed;
    private OffsetDateTime updatedAt;
}
""",

    "SkillGapResponseBean.java": """package com.skillops.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapResponseBean {
    private Long id;
    private Long profileId;
    private Long skillId;
    private Integer currentLevel;
    private Integer targetLevel;
    private Integer gapScore;
    private String priority;
}
""",

    "CourseResponseBean.java": """package com.skillops.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseBean {
    private Long id;
    private Long learningPathId;
    private String title;
    private String description;
    private String instructor;
    private Integer durationHours;
    private String level;
    private Boolean published;
}
""",

    "LessonResponseBean.java": """package com.skillops.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponseBean {
    private Long id;
    private Long moduleId;
    private String title;
    private String lessonType;
    private Integer durationMinutes;
    private String videoUrl;
    private Boolean downloadable;
}
""",

    "EnrollmentResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseBean {
    private Long id;
    private Long profileId;
    private Long courseId;
    private LocalDate enrollmentDate;
    private String status;
    private Integer completionPercentage;
}
""",

    "LearningProgressResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningProgressResponseBean {
    private Long id;
    private Long enrollmentId;
    private Long lessonId;
    private Boolean completed;
    private Integer watchPercentage;
    private OffsetDateTime completedAt;
}
""",

    "ProjectResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseBean {
    private Long id;
    private Long profileId;
    private Long courseId;
    private String title;
    private String description;
    private String projectType;
    private String difficultyLevel;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String githubRepository;
    private OffsetDateTime createdAt;
}
""",

    "ProjectMilestoneResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMilestoneResponseBean {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private Integer sequence;
    private LocalDate dueDate;
    private Boolean completed;
}
""",

    "ProjectSubmissionResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSubmissionResponseBean {
    private Long id;
    private Long projectId;
    private Long profileId;
    private String repositoryUrl;
    private String liveDemoUrl;
    private String documentationUrl;
    private String submissionStatus;
    private OffsetDateTime submittedAt;
}
""",

    "PortfolioItemResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioItemResponseBean {
    private Long id;
    private Long profileId;
    private Long projectId;
    private String title;
    private String thumbnailUri;
    private String liveUrl;
    private String githubUrl;
    private Boolean featured;
    private Integer views;
    private Integer likes;
    private OffsetDateTime publishedAt;
}
""",

    "AssessmentResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResponseBean {
    private Long id;
    private String title;
    private String assessmentType;
    private String difficulty;
    private Integer totalMarks;
    private Integer durationMinutes;
    private Integer passingMarks;
    private Boolean isPublished;
    private OffsetDateTime createdAt;
}
""",

    "AssessmentAttemptResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentAttemptResponseBean {
    private Long id;
    private Long assessmentId;
    private Long profileId;
    private OffsetDateTime startedAt;
    private OffsetDateTime submittedAt;
    private Integer obtainedMarks;
    private Boolean passed;
    private String status;
}
""",

    "CodingChallengeResponseBean.java": """package com.skillops.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodingChallengeResponseBean {
    private Long id;
    private Long assessmentId;
    private String title;
    private String language;
    private String difficulty;
    private Integer maxScore;
}
""",

    "CertificateResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateResponseBean {
    private Long id;
    private Long assessmentId;
    private Long profileId;
    private String certificateNumber;
    private String certificateUrl;
    private LocalDate issuedOn;
    private String verificationHash;
}
""",

    "CompanyResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseBean {
    private Long id;
    private String companyName;
    private String industry;
    private String website;
    private String headquarters;
    private Integer employeeCount;
    private String companyType;
    private Boolean verified;
    private OffsetDateTime createdAt;
}
""",

    "JobResponseBean.java": """package com.skillops.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseBean {
    private Long id;
    private Long companyId;
    private String jobTitle;
    private String department;
    private String employmentType;
    private String workMode;
    private String location;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String experienceLevel;
    private LocalDate applicationDeadline;
    private String status;
}
""",

    "JobApplicationResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationResponseBean {
    private Long id;
    private Long jobId;
    private Long profileId;
    private Long resumeId;
    private LocalDate appliedOn;
    private String applicationStatus;
    private Integer aiMatchScore;
    private Boolean shortlisted;
}
""",

    "CandidateMatchResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateMatchResponseBean {
    private Long id;
    private Long applicationId;
    private Integer skillScore;
    private Integer projectScore;
    private Integer assessmentScore;
    private Integer interviewScore;
    private Integer overallScore;
    private String recommendation;
    private OffsetDateTime generatedAt;
}
""",

    "OfferResponseBean.java": """package com.skillops.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponseBean {
    private Long id;
    private Long applicationId;
    private String designation;
    private BigDecimal offeredCtc;
    private LocalDate joiningDate;
    private LocalDate offerExpiry;
    private String offerStatus;
}
""",

    "HiringStatusResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HiringStatusResponseBean {
    private Long id;
    private Long applicationId;
    private String currentStage;
    private Boolean backgroundVerification;
    private Boolean documentsVerified;
    private Boolean onboardingCompleted;
    private OffsetDateTime updatedAt;
}
""",

    "InterviewResponseBean.java": """package com.skillops.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewResponseBean {
    private Long id;
    private Long applicationId;
    private Long candidateProfileId;
    private String interviewType;
    private String mode;
    private LocalDate scheduledDate;
    private LocalTime scheduledTime;
    private Integer durationMinutes;
    private String status;
    private String meetingLink;
    private OffsetDateTime createdAt;
}
""",

    "InterviewRoundResponseBean.java": """package com.skillops.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewRoundResponseBean {
    private Long id;
    private Long interviewId;
    private String roundName;
    private String roundType;
    private Integer roundNumber;
    private String interviewer;
    private Integer durationMinutes;
    private String status;
}
""",

    "InterviewScorecardResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewScorecardResponseBean {
    private Long id;
    private Long interviewId;
    private Integer communicationScore;
    private Integer technicalScore;
    private Integer aptitudeScore;
    private Integer behavioralScore;
    private Integer codingScore;
    private Integer finalScore;
    private String recommendation;
    private OffsetDateTime generatedAt;
}
""",

    "AiInterviewReportResponseBean.java": """package com.skillops.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiInterviewReportResponseBean {
    private Long id;
    private Long interviewId;
    private Long scorecardId;
    private BigDecimal confidenceScore;
    private String strengths;
    private String weaknesses;
    private String improvementAreas;
    private String suggestedLearningPath;
    private String aiSummary;
    private OffsetDateTime generatedAt;
}
""",

    "NotificationResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseBean {
    private Long id;
    private Long userId;
    private Long preferenceId;
    private String title;
    private String message;
    private String notificationType;
    private String deliveryChannel;
    private String priority;
    private String status;
    private String referenceModule;
    private Long referenceId;
    private Boolean isRead;
    private OffsetDateTime scheduledAt;
    private OffsetDateTime sentAt;
    private OffsetDateTime createdAt;
}
""",

    "NotificationPreferenceResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPreferenceResponseBean {
    private Long id;
    private Long userId;
    private Boolean emailEnabled;
    private Boolean pushEnabled;
    private Boolean smsEnabled;
    private Boolean whatsappEnabled;
    private Boolean inAppEnabled;
    private Boolean marketingEnabled;
    private Boolean reminderEnabled;
    private Boolean systemAlertsEnabled;
    private String preferredLanguage;
    private String timezone;
    private OffsetDateTime updatedAt;
}
""",

    "DashboardResponseBean.java": """package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseBean {
    private Long id;
    private Long profileId;
    private String dashboardType;
    private String dashboardName;
    private Integer widgetsCount;
    private Boolean isDefault;
    private OffsetDateTime lastRefreshed;
    private OffsetDateTime createdAt;
}
""",

    "UserAnalyticsResponseBean.java": """package com.skillops.response;

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
"""
}

for filename, content in beans.items():
    filepath = os.path.join(target_dir, filename)
    with open(filepath, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Created: {filepath}")

print(f"Total response bean files created: {len(beans)}")
