package com.skillops.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealthStatus() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "system", "SkillOps AI-Powered Workforce OS",
            "timestamp", Instant.now().toString(),
            "version", "3.4.5"
        ));
    }
}
