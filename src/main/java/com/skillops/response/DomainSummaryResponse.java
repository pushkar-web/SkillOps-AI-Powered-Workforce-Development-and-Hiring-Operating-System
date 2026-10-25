package com.skillops.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomainSummaryResponse {
    private String domainName;
    private int domainCode;
    private Map<String, Long> metrics;
    private String status;
}
