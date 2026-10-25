package com.skillops.domain6_assessments.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentQuestionResponseBean {
    private Long id;
    private Long assessmentId;
    private String questionType;
    private String question;
    private Integer marks;
    private Integer sequence;
}
