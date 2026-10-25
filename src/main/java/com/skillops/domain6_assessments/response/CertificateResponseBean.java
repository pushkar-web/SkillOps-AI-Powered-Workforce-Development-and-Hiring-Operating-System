package com.skillops.domain6_assessments.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateResponseBean {
    private Long id;
    private Long assessmentId;
    private Long profileId;
    private String certificateNumber;
    private String certificateUrl;
    private LocalDate issuedOn;
    private String verificationHash;
}
