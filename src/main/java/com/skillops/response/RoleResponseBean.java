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
public class RoleResponseBean {
    private Long id;
    private String name;
    private String description;
    private OffsetDateTime createdAt;
}
