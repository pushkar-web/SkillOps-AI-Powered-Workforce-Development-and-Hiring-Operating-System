package com.skillops.domain4_learning.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseBean {
    private Long id;
    private Long learningPathId;
    private String title;
    private String description;
    private String instructor;
    private Integer durationHours;
    private String level;
    private Boolean published;
}
