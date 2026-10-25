package com.skillops.domain4_learning.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponseBean {
    private Long id;
    private Long lessonId;
    private String title;
    private Integer totalMarks;
    private Integer passingMarks;
    private Integer durationMinutes;
}
