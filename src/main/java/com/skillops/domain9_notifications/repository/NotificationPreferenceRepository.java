package com.skillops.domain9_notifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain9_notifications.entity.NotificationPreference;

public interface NotificationPreferenceRepository extends JpaRepository<NotificationPreference, Long> {
}
