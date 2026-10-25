package com.skillops.domain5_projects.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioItemResponseBean {
    private Long id;
    private Long profileId;
    private Long projectId;
    private String title;
    private String thumbnailUri;
    private String liveUrl;
    private String githubUrl;
    private Boolean featured;
    private Integer views;
    private Integer likes;
    private OffsetDateTime publishedAt;
}
