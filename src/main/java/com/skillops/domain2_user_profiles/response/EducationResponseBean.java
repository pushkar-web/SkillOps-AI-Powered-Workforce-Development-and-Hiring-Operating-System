package com.skillops.domain2_user_profiles.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseBean {
    private Long id;
    private Long profileId;
    private String instituteName;
    private String degree;
    private String specialization;
    private BigDecimal cgpa;
    private Integer passingYear;
    private Integer startYear;
    private Integer endYear;
    private Boolean isCurrent;
}
