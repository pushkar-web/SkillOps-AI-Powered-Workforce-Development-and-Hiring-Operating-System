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
public class AuditLogResponseBean {
    private Long id;
    private Long userId;
    private String action;
    private String entityName;
    private Long entityId;
    private String oldValue;
    private String newValue;
    private OffsetDateTime timestamp;
}
