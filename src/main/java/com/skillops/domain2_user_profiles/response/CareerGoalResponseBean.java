package com.skillops.domain2_user_profiles.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerGoalResponseBean {
    private Long id;
    private Long profileId;
    private String targetRole;
    private String targetIndustry;
    private String targetCompany;
    private String preferredLocation;
    private BigDecimal expectedCtc;
    private String employmentType;
    private Integer targetTimelineMonths;
    private OffsetDateTime createdAt;
}
