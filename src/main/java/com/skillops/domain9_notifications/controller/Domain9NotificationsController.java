package com.skillops.domain9_notifications.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skillops.common.CrudFacade;
import com.skillops.config.OpenApiConfig;
import com.skillops.domain9_notifications.entity.AppNotification;
import com.skillops.domain9_notifications.entity.NotificationPreference;
import com.skillops.domain9_notifications.repository.AppNotificationRepository;
import com.skillops.domain9_notifications.repository.NotificationPreferenceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/notifications-domain")
@Tag(name = OpenApiConfig.DOMAIN_9)
public class Domain9NotificationsController {

    private final CrudFacade crud;
    private final NotificationPreferenceRepository notificationPreferenceRepository;
    private final AppNotificationRepository appNotificationRepository;

    public Domain9NotificationsController(
            CrudFacade crud,
            NotificationPreferenceRepository notificationPreferenceRepository,
            AppNotificationRepository appNotificationRepository
    ) {
        this.crud = crud;
        this.notificationPreferenceRepository = notificationPreferenceRepository;
        this.appNotificationRepository = appNotificationRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_9);
        body.put("notification-preferences", notificationPreferenceRepository.count());
        body.put("notifications", appNotificationRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/notification-preferences")
    @Operation(summary = "GET all NotificationPreference records")
    public List<NotificationPreference> listNotificationPreference() {
        return crud.findAll(notificationPreferenceRepository);
    }

    @GetMapping("/notification-preferences/{id}")
    @Operation(summary = "GET NotificationPreference by id")
    public NotificationPreference getNotificationPreference(@PathVariable Long id) {
        return crud.get(notificationPreferenceRepository, id, "NotificationPreference");
    }

    @PostMapping("/notification-preferences")
    @Operation(summary = "POST create NotificationPreference")
    public ResponseEntity<NotificationPreference> createNotificationPreference(@Valid @RequestBody NotificationPreference body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(notificationPreferenceRepository, body));
    }

    @PutMapping("/notification-preferences/{id}")
    @Operation(summary = "PUT replace NotificationPreference")
    public NotificationPreference replaceNotificationPreference(@PathVariable Long id, @Valid @RequestBody NotificationPreference body) {
        return crud.replace(notificationPreferenceRepository, id, body, "NotificationPreference");
    }

    @PatchMapping("/notification-preferences/{id}")
    @Operation(summary = "PATCH NotificationPreference")
    public NotificationPreference patchNotificationPreference(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(notificationPreferenceRepository, id, updates, "NotificationPreference");
    }

    @DeleteMapping("/notification-preferences/{id}")
    @Operation(summary = "DELETE NotificationPreference")
    public ResponseEntity<Void> deleteNotificationPreference(@PathVariable Long id) {
        crud.delete(notificationPreferenceRepository, id, "NotificationPreference");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/notifications")
    @Operation(summary = "GET all AppNotification records")
    public List<AppNotification> listAppNotification() {
        return crud.findAll(appNotificationRepository);
    }

    @GetMapping("/notifications/{id}")
    @Operation(summary = "GET AppNotification by id")
    public AppNotification getAppNotification(@PathVariable Long id) {
        return crud.get(appNotificationRepository, id, "AppNotification");
    }

    @PostMapping("/notifications")
    @Operation(summary = "POST create AppNotification")
    public ResponseEntity<AppNotification> createAppNotification(@Valid @RequestBody AppNotification body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(appNotificationRepository, body));
    }

    @PutMapping("/notifications/{id}")
    @Operation(summary = "PUT replace AppNotification")
    public AppNotification replaceAppNotification(@PathVariable Long id, @Valid @RequestBody AppNotification body) {
        return crud.replace(appNotificationRepository, id, body, "AppNotification");
    }

    @PatchMapping("/notifications/{id}")
    @Operation(summary = "PATCH AppNotification")
    public AppNotification patchAppNotification(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(appNotificationRepository, id, updates, "AppNotification");
    }

    @DeleteMapping("/notifications/{id}")
    @Operation(summary = "DELETE AppNotification")
    public ResponseEntity<Void> deleteAppNotification(@PathVariable Long id) {
        crud.delete(appNotificationRepository, id, "AppNotification");
        return ResponseEntity.noContent().build();
    }

}
