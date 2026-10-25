package com.skillops.domain5_projects.response;

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
