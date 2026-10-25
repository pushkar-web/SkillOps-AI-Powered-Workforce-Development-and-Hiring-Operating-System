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
public class RefreshTokenResponseBean {
    private Long id;
    private Long userId;
    private String token;
    private OffsetDateTime expiryDate;
}
