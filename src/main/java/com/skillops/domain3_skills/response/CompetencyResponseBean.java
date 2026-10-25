package com.skillops.domain3_skills.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetencyResponseBean {
    private Long id;
    private Long skillId;
    private String competencyName;
    private String description;
    private Integer displayOrder;
}
