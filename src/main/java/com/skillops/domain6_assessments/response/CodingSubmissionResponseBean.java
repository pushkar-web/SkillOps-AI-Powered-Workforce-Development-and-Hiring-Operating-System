package com.skillops.domain6_assessments.response;

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
