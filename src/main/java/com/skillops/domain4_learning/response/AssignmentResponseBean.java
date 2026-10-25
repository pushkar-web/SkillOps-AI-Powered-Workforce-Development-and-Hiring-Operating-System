package com.skillops.domain4_learning.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentResponseBean {
    private Long id;
    private Long lessonId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Integer maxScore;
}
