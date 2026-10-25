package com.skillops.domain3_skills.response;

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
