package com.skillops.domain4_learning.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponseBean {
    private Long id;
    private Long moduleId;
    private String title;
    private String lessonType;
    private Integer durationMinutes;
    private String videoUrl;
    private Boolean downloadable;
}
