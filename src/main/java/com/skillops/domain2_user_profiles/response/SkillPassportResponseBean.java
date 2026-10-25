package com.skillops.domain2_user_profiles.response;

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
