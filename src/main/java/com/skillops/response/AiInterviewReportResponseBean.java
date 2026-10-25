package com.skillops.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiInterviewReportResponseBean {
    private Long id;
    private Long interviewId;
    private Long scorecardId;
    private BigDecimal confidenceScore;
    private String strengths;
    private String weaknesses;
    private String improvementAreas;
    private String suggestedLearningPath;
    private String aiSummary;
    private OffsetDateTime generatedAt;
}
