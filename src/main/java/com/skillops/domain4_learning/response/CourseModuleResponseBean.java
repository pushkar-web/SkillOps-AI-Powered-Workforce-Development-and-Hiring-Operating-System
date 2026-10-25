package com.skillops.domain4_learning.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseModuleResponseBean {
    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private Integer sequence;
}
