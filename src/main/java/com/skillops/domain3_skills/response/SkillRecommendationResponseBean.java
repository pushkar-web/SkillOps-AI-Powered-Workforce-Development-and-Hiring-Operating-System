package com.skillops.domain3_skills.response;

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
