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
public class SkillResponseBean {
    private Long id;
    private Long categoryId;
    private String skillName;
    private String description;
    private String difficultyLevel;
    private Boolean isVerified;
    private OffsetDateTime createdAt;
}
