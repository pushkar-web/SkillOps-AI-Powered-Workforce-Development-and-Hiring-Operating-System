package com.skillops.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseBean {
    private Long id;
    private Long profileId;
    private Long courseId;
    private LocalDate enrollmentDate;
    private String status;
    private Integer completionPercentage;
}
