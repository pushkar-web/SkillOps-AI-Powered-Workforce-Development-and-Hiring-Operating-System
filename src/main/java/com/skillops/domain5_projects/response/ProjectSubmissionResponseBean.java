package com.skillops.domain5_projects.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSubmissionResponseBean {
    private Long id;
    private Long projectId;
    private Long profileId;
    private String repositoryUrl;
    private String liveDemoUrl;
    private String documentationUrl;
    private String submissionStatus;
    private OffsetDateTime submittedAt;
}
