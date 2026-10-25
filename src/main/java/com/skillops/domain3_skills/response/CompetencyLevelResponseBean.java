package com.skillops.domain3_skills.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetencyLevelResponseBean {
    private Long id;
    private Long competencyId;
    private String levelName;
    private Integer levelValue;
    private String criteria;
}
