package com.skillops.domain1_identity_authentication.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryResponseBean {
    private Long id;
    private Long userId;
    private OffsetDateTime loginTime;
    private OffsetDateTime logoutTime;
    private String device;
    private String browser;
    private String ipAddress;
}
