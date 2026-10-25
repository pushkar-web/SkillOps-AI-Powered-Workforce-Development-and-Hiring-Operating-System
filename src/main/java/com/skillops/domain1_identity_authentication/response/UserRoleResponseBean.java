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
public class UserRoleResponseBean {
    private Long id;
    private Long userId;
    private Long roleId;
    private OffsetDateTime assignedAt;
}
