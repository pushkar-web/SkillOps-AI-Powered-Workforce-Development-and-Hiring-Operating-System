package com.skillops.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationResponseBean {
    private Long id;
    private Long jobId;
    private Long profileId;
    private Long resumeId;
    private LocalDate appliedOn;
    private String applicationStatus;
    private Integer aiMatchScore;
    private Boolean shortlisted;
}
