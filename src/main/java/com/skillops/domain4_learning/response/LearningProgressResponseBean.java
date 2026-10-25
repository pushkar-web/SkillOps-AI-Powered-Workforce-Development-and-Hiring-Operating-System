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
public class LearningProgressResponseBean {
    private Long id;
    private Long enrollmentId;
    private Long lessonId;
    private Boolean completed;
    private Integer watchPercentage;
    private OffsetDateTime completedAt;
}
