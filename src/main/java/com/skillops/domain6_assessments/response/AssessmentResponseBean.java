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
public class AssessmentResponseBean {
    private Long id;
    private String title;
    private String assessmentType;
    private String difficulty;
    private Integer totalMarks;
    private Integer durationMinutes;
    private Integer passingMarks;
    private Boolean isPublished;
    private OffsetDateTime createdAt;
}
