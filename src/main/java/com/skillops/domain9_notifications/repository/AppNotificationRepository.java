package com.skillops.domain9_notifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain9_notifications.entity.AppNotification;

public interface AppNotificationRepository extends JpaRepository<AppNotification, Long> {
}
