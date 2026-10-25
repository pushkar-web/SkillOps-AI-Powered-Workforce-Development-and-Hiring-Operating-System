package com.skillops.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseBean {
    private Long id;
    private Long profileId;
    private Long courseId;
    private String title;
    private String description;
    private String projectType;
    private String difficultyLevel;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String githubRepository;
    private OffsetDateTime createdAt;
}
