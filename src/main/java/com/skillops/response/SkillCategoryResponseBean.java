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
public class SkillCategoryResponseBean {
    private Long id;
    private String categoryName;
    private String description;
    private String icon;
    private Boolean isActive;
    private OffsetDateTime createdAt;
}
