package com.skillops.domain3_skills.response;

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
