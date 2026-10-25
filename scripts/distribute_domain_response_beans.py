import os

BASE_DIR = os.path.join("src", "main", "java", "com", "skillops")

domains = {
    "domain1_identity_authentication": {
        "pkg": "com.skillops.domain1_identity_authentication.response",
        "beans": {
            "UserResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

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
            "RoleResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

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
            "PermissionResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseBean {
    private Long id;
    private String module;
    private String action;
    private String description;
}
""",
            "UserRoleResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponseBean {
    private Long id;
    private Long userId;
    private Long roleId;
    private OffsetDateTime assignedAt;
}
""",
            "SessionResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

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
            "RefreshTokenResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenResponseBean {
    private Long id;
    private Long userId;
    private String token;
    private OffsetDateTime expiryDate;
}
""",
            "LoginHistoryResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryResponseBean {
    private Long id;
    private Long userId;
    private OffsetDateTime loginTime;
    private OffsetDateTime logoutTime;
    private String device;
    private String browser;
    private String ipAddress;
}
""",
            "AuditLogResponseBean.java": """package com.skillops.domain1_identity_authentication.response;

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
"""
        }
    },

    "domain2_user_profiles": {
        "pkg": "com.skillops.domain2_user_profiles.response",
        "beans": {
            "ProfileResponseBean.java": """package com.skillops.domain2_user_profiles.response;

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
            "ResumeResponseBean.java": """package com.skillops.domain2_user_profiles.response;

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
            "EducationResponseBean.java": """package com.skillops.domain2_user_profiles.response;

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
            "ExperienceResponseBean.java": """package com.skillops.domain2_user_profiles.response;

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
            "ProfileCertificationResponseBean.java": """package com.skillops.domain2_user_profiles.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCertificationResponseBean {
    private Long id;
    private Long profileId;
    private String certificationName;
    private String issuer;
    private String credentialId;
    private String credentialUrl;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}
""",
            "CareerGoalResponseBean.java": """package com.skillops.domain2_user_profiles.response;

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
public class CareerGoalResponseBean {
    private Long id;
    private Long profileId;
    private String targetRole;
    private String targetIndustry;
    private String targetCompany;
    private String preferredLocation;
    private BigDecimal expectedCtc;
    private String employmentType;
    private Integer targetTimelineMonths;
    private OffsetDateTime createdAt;
}
""",
            "SkillPassportResponseBean.java": """package com.skillops.domain2_user_profiles.response;

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
"""
        }
    },

    "domain3_skills": {
        "pkg": "com.skillops.domain3_skills.response",
        "beans": {
            "SkillCategoryResponseBean.java": """package com.skillops.domain3_skills.response;

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
            "SkillResponseBean.java": """package com.skillops.domain3_skills.response;

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
            "SkillAssessmentResponseBean.java": """package com.skillops.domain3_skills.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillAssessmentResponseBean {
    private Long id;
    private Long skillId;
    private String assessmentType;
    private Integer passingScore;
    private Integer totalMarks;
    private Integer durationMinutes;
}
""",
            "CompetencyResponseBean.java": """package com.skillops.domain3_skills.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetencyResponseBean {
    private Long id;
    private Long skillId;
    private String competencyName;
    private String description;
    private Integer displayOrder;
}
""",
            "CompetencyLevelResponseBean.java": """package com.skillops.domain3_skills.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetencyLevelResponseBean {
    private Long id;
    private Long competencyId;
    private String levelName;
    private Integer levelValue;
    private String criteria;
}
""",
            "UserSkillResponseBean.java": """package com.skillops.domain3_skills.response;

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
            "SkillGapResponseBean.java": """package com.skillops.domain3_skills.response;

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
            "SkillRecommendationResponseBean.java": """package com.skillops.domain3_skills.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillRecommendationResponseBean {
    private Long id;
    private Long profileId;
    private Long skillId;
    private String recommendationReason;
    private Integer recommendationScore;
    private String recommendationType;
    private OffsetDateTime generatedAt;
}
"""
        }
    },

    "domain4_learning": {
        "pkg": "com.skillops.domain4_learning.response",
        "beans": {
            "LearningPathResponseBean.java": """package com.skillops.domain4_learning.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningPathResponseBean {
    private Long id;
    private Long profileId;
    private String title;
    private String description;
    private String difficulty;
    private Integer estimatedDuration;
    private String status;
    private OffsetDateTime createdAt;
}
""",
            "CourseResponseBean.java": """package com.skillops.domain4_learning.response;

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
            "CourseModuleResponseBean.java": """package com.skillops.domain4_learning.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseModuleResponseBean {
    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private Integer sequence;
}
""",
            "LessonResponseBean.java": """package com.skillops.domain4_learning.response;

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
            "AssignmentResponseBean.java": """package com.skillops.domain4_learning.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentResponseBean {
    private Long id;
    private Long lessonId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Integer maxScore;
}
""",
            "SubmissionResponseBean.java": """package com.skillops.domain4_learning.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponseBean {
    private Long id;
    private Long assignmentId;
    private Long profileId;
    private String repositoryUrl;
    private String fileUrl;
    private Integer score;
    private String status;
    private OffsetDateTime submittedAt;
}
""",
            "QuizResponseBean.java": """package com.skillops.domain4_learning.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponseBean {
    private Long id;
    private Long lessonId;
    private String title;
    private Integer totalMarks;
    private Integer passingMarks;
    private Integer durationMinutes;
}
""",
            "QuizAttemptResponseBean.java": """package com.skillops.domain4_learning.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptResponseBean {
    private Long id;
    private Long quizId;
    private Long profileId;
    private Integer obtainedMarks;
    private Boolean passed;
    private Integer attemptNumber;
    private OffsetDateTime attemptedAt;
}
""",
            "EnrollmentResponseBean.java": """package com.skillops.domain4_learning.response;

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
            "LearningProgressResponseBean.java": """package com.skillops.domain4_learning.response;

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
"""
        }
    },

    "domain5_projects": {
        "pkg": "com.skillops.domain5_projects.response",
        "beans": {
            "ProjectResponseBean.java": """package com.skillops.domain5_projects.response;

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
            "ProjectMilestoneResponseBean.java": """package com.skillops.domain5_projects.response;

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
            "ProjectSubmissionResponseBean.java": """package com.skillops.domain5_projects.response;

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
            "ProjectReviewResponseBean.java": """package com.skillops.domain5_projects.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectReviewResponseBean {
    private Long id;
    private Long submissionId;
    private Long reviewerId;
    private Integer technicalScore;
    private Integer designScore;
    private Integer documentationScore;
    private Integer innovationScore;
    private Integer overallScore;
    private String feedback;
    private OffsetDateTime reviewedAt;
}
""",
            "PortfolioItemResponseBean.java": """package com.skillops.domain5_projects.response;

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
"""
        }
    },

    "domain6_assessments": {
        "pkg": "com.skillops.domain6_assessments.response",
        "beans": {
            "AssessmentResponseBean.java": """package com.skillops.domain6_assessments.response;

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
            "AssessmentQuestionResponseBean.java": """package com.skillops.domain6_assessments.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentQuestionResponseBean {
    private Long id;
    private Long assessmentId;
    private String questionType;
    private String question;
    private Integer marks;
    private Integer sequence;
}
""",
            "CodingChallengeResponseBean.java": """package com.skillops.domain6_assessments.response;

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
            "AssessmentAttemptResponseBean.java": """package com.skillops.domain6_assessments.response;

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
            "CodingSubmissionResponseBean.java": """package com.skillops.domain6_assessments.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodingSubmissionResponseBean {
    private Long id;
    private Long challengeId;
    private Long profileId;
    private String repositoryUrl;
    private String sourceCodeUrl;
    private Integer executionScore;
    private Integer qualityScore;
    private OffsetDateTime submittedAt;
}
""",
            "EvaluationResultResponseBean.java": """package com.skillops.domain6_assessments.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResultResponseBean {
    private Long id;
    private Long attemptId;
    private Long codingSubmissionId;
    private Integer objectiveScore;
    private Integer codingScore;
    private Integer aiScore;
    private Integer finalScore;
    private String remarks;
}
""",
            "CertificateResponseBean.java": """package com.skillops.domain6_assessments.response;

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
            "AssessmentFeedbackResponseBean.java": """package com.skillops.domain6_assessments.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentFeedbackResponseBean {
    private Long id;
    private Long evaluationResultId;
    private String strengths;
    private String weaknesses;
    private String recommendations;
    private OffsetDateTime generatedAt;
}
"""
        }
    },

    "domain7_interviews": {
        "pkg": "com.skillops.domain7_interviews.response",
        "beans": {
            "InterviewResponseBean.java": """package com.skillops.domain7_interviews.response;

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
            "InterviewRoundResponseBean.java": """package com.skillops.domain7_interviews.response;

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
            "InterviewFeedbackResponseBean.java": """package com.skillops.domain7_interviews.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewFeedbackResponseBean {
    private Long id;
    private Long roundId;
    private Long reviewerId;
    private Integer communicationRating;
    private Integer technicalRating;
    private Integer problemSolvingRating;
    private Integer confidenceRating;
    private String strengths;
    private String improvements;
    private String remarks;
    private OffsetDateTime submittedAt;
}
""",
            "InterviewScorecardResponseBean.java": """package com.skillops.domain7_interviews.response;

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
            "AiInterviewReportResponseBean.java": """package com.skillops.domain7_interviews.response;

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
"""
        }
    },

    "domain8_recruitment": {
        "pkg": "com.skillops.domain8_recruitment.response",
        "beans": {
            "CompanyResponseBean.java": """package com.skillops.domain8_recruitment.response;

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
            "JobResponseBean.java": """package com.skillops.domain8_recruitment.response;

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
            "JobRequirementResponseBean.java": """package com.skillops.domain8_recruitment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequirementResponseBean {
    private Long id;
    private Long jobId;
    private String requirementType;
    private String description;
    private Boolean mandatory;
    private Integer priority;
}
""",
            "JobApplicationResponseBean.java": """package com.skillops.domain8_recruitment.response;

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
            "CandidateMatchResponseBean.java": """package com.skillops.domain8_recruitment.response;

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
            "OfferResponseBean.java": """package com.skillops.domain8_recruitment.response;

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
            "HiringStatusResponseBean.java": """package com.skillops.domain8_recruitment.response;

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
"""
        }
    },

    "domain9_notifications": {
        "pkg": "com.skillops.domain9_notifications.response",
        "beans": {
            "NotificationResponseBean.java": """package com.skillops.domain9_notifications.response;

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
            "NotificationPreferenceResponseBean.java": """package com.skillops.domain9_notifications.response;

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
"""
        }
    },

    "domain10_analytics": {
        "pkg": "com.skillops.domain10_analytics.response",
        "beans": {
            "DashboardResponseBean.java": """package com.skillops.domain10_analytics.response;

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
            "UserAnalyticsResponseBean.java": """package com.skillops.domain10_analytics.response;

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
    }
}

count = 0
for domain_folder, data in domains.items():
    # 1. Ensure dto folder exists in each domain
    dto_dir = os.path.join(BASE_DIR, domain_folder, "dto")
    os.makedirs(dto_dir, exist_ok=True)
    dto_pkg = f"com.skillops.{domain_folder}.dto"
    dto_sample = os.path.join(dto_dir, "package-info.java")
    if not os.path.exists(dto_sample) and len(os.listdir(dto_dir)) == 0:
        with open(dto_sample, "w", encoding="utf-8") as f:
            f.write(f"/**\n * Data Transfer Objects for {domain_folder}.\n */\npackage {dto_pkg};\n")

    # 2. Create response folder in each domain
    resp_dir = os.path.join(BASE_DIR, domain_folder, "response")
    os.makedirs(resp_dir, exist_ok=True)
    
    # 3. Write domain response beans
    for filename, code in data["beans"].items():
        filepath = os.path.join(resp_dir, filename)
        with open(filepath, "w", encoding="utf-8") as f:
            f.write(code)
        count += 1
        print(f"[{domain_folder}] Created response bean: {filename}")

print(f"\nSuccessfully distributed {count} response beans into all 10 domain response folders!")
