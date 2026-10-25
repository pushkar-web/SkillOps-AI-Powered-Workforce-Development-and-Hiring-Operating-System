package com.skillops.domain1_identity_authentication.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseBean {
    private Long id;
    private String module;
    private String action;
    private String description;
}
