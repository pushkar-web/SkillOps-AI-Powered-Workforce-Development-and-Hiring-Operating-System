package com.skillops.domain8_recruitment.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseBean {
    private Long id;
    private Long companyId;
    private String jobTitle;
    private String department;
    private String employmentType;
    private String workMode;
    private String location;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String experienceLevel;
    private LocalDate applicationDeadline;
    private String status;
}
