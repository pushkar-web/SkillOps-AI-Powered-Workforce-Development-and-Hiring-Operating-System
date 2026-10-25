package com.skillops.domain10_analytics.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseBean {
    private Long id;
    private Long profileId;
    private String dashboardType;
    private String dashboardName;
    private Integer widgetsCount;
    private Boolean isDefault;
    private OffsetDateTime lastRefreshed;
    private OffsetDateTime createdAt;
}
