package com.skillops.domain8_recruitment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequirementResponseBean {
    private Long id;
    private Long jobId;
    private String requirementType;
    private String description;
    private Boolean mandatory;
    private Integer priority;
}
