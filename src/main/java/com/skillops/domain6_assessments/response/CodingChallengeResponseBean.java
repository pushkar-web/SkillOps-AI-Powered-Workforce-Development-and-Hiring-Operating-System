package com.skillops.domain6_assessments.response;

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
