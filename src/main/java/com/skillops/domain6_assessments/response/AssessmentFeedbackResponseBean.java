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
public class AssessmentFeedbackResponseBean {
    private Long id;
    private Long evaluationResultId;
    private String strengths;
    private String weaknesses;
    private String recommendations;
    private OffsetDateTime generatedAt;
}
