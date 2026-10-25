package com.skillops.domain4_learning.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttemptResponseBean {
    private Long id;
    private Long quizId;
    private Long profileId;
    private Integer obtainedMarks;
    private Boolean passed;
    private Integer attemptNumber;
    private OffsetDateTime attemptedAt;
}
