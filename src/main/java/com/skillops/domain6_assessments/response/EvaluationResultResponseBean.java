package com.skillops.domain6_assessments.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResultResponseBean {
    private Long id;
    private Long attemptId;
    private Long codingSubmissionId;
    private Integer objectiveScore;
    private Integer codingScore;
    private Integer aiScore;
    private Integer finalScore;
    private String remarks;
}
