package com.skillops.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HiringStatusResponseBean {
    private Long id;
    private Long applicationId;
    private String currentStage;
    private Boolean backgroundVerification;
    private Boolean documentsVerified;
    private Boolean onboardingCompleted;
    private OffsetDateTime updatedAt;
}
