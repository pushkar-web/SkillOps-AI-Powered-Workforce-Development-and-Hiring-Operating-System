package com.skillops.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewResponseBean {
    private Long id;
    private Long applicationId;
    private Long candidateProfileId;
    private String interviewType;
    private String mode;
    private LocalDate scheduledDate;
    private LocalTime scheduledTime;
    private Integer durationMinutes;
    private String status;
    private String meetingLink;
    private OffsetDateTime createdAt;
}
