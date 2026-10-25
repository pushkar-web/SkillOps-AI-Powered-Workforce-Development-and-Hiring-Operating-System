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
public class SessionResponseBean {
    private Long id;
    private Long userId;
    private String ipAddress;
    private String device;
    private OffsetDateTime expiresAt;
}
