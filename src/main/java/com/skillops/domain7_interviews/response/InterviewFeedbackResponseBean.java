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
public class InterviewFeedbackResponseBean {
    private Long id;
    private Long roundId;
    private Long reviewerId;
    private Integer communicationRating;
    private Integer technicalRating;
    private Integer problemSolvingRating;
    private Integer confidenceRating;
    private String strengths;
    private String improvements;
    private String remarks;
    private OffsetDateTime submittedAt;
}
