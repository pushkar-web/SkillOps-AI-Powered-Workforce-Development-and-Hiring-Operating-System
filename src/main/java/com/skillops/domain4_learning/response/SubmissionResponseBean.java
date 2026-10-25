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
public class SubmissionResponseBean {
    private Long id;
    private Long assignmentId;
    private Long profileId;
    private String repositoryUrl;
    private String fileUrl;
    private Integer score;
    private String status;
    private OffsetDateTime submittedAt;
}
