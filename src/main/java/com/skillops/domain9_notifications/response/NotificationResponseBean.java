package com.skillops.domain9_notifications.response;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseBean {
    private Long id;
    private Long userId;
    private Long preferenceId;
    private String title;
    private String message;
    private String notificationType;
    private String deliveryChannel;
    private String priority;
    private String status;
    private String referenceModule;
    private Long referenceId;
    private Boolean isRead;
    private OffsetDateTime scheduledAt;
    private OffsetDateTime sentAt;
    private OffsetDateTime createdAt;
}
