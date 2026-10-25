package com.skillops.domain5_projects.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMilestoneResponseBean {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private Integer sequence;
    private LocalDate dueDate;
    private Boolean completed;
}
