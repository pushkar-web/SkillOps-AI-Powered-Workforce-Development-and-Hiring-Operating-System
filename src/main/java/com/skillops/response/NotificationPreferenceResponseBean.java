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
public class NotificationPreferenceResponseBean {
    private Long id;
    private Long userId;
    private Boolean emailEnabled;
    private Boolean pushEnabled;
    private Boolean smsEnabled;
    private Boolean whatsappEnabled;
    private Boolean inAppEnabled;
    private Boolean marketingEnabled;
    private Boolean reminderEnabled;
    private Boolean systemAlertsEnabled;
    private String preferredLanguage;
    private String timezone;
    private OffsetDateTime updatedAt;
}
