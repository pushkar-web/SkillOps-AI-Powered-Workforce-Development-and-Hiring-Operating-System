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
public class ResumeResponseBean {
    private Long id;
    private Long profileId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Integer version;
    private Boolean isPrimary;
    private OffsetDateTime uploadedAt;
}
