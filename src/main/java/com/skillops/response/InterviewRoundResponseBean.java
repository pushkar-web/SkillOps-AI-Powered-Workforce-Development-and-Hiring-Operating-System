package com.skillops.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewRoundResponseBean {
    private Long id;
    private Long interviewId;
    private String roundName;
    private String roundType;
    private Integer roundNumber;
    private String interviewer;
    private Integer durationMinutes;
    private String status;
}
