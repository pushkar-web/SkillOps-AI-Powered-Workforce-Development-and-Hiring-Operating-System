package com.skillops.domain3_skills.response;

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
