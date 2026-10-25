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
