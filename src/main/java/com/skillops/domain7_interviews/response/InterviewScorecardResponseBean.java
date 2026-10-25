package com.skillops.domain7_interviews.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewScorecardResponseBean {
    private Long id;
    private Long interviewId;
    private Integer communicationScore;
    private Integer technicalScore;
    private Integer aptitudeScore;
    private Integer behavioralScore;
    private Integer codingScore;
    private Integer finalScore;
    private String recommendation;
    private OffsetDateTime generatedAt;
}
