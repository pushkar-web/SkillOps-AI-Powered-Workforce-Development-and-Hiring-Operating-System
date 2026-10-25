package com.skillops.domain8_recruitment.response;

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
