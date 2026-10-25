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
public class CompanyResponseBean {
    private Long id;
    private String companyName;
    private String industry;
    private String website;
    private String headquarters;
    private Integer employeeCount;
    private String companyType;
    private Boolean verified;
    private OffsetDateTime createdAt;
}
