package com.skillops.domain9_notifications.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notification_preferences", schema = "notifications")
public class NotificationPreference {

    public NotificationPreference() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "email_enabled", nullable = false)
    private Boolean emailEnabled;

    @Column(name = "push_enabled", nullable = false)
    private Boolean pushEnabled;

    @Column(name = "sms_enabled", nullable = false)
    private Boolean smsEnabled;

    @Column(name = "whatsapp_enabled", nullable = false)
    private Boolean whatsappEnabled;

    @Column(name = "in_app_enabled", nullable = false)
    private Boolean inAppEnabled;

    @Column(name = "marketing_enabled", nullable = false)
    private Boolean marketingEnabled;

    @Column(name = "reminder_enabled", nullable = false)
    private Boolean reminderEnabled;

    @Column(name = "system_alerts_enabled", nullable = false)
    private Boolean systemAlertsEnabled;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


    @OneToMany(mappedBy = "preference", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AppNotification> notifications = new ArrayList<>();
}
