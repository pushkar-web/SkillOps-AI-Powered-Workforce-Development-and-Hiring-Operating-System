package com.skillops.domain10_analytics.controller;

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
import com.skillops.domain10_analytics.entity.Dashboard;
import com.skillops.domain10_analytics.entity.UserAnalytics;
import com.skillops.domain10_analytics.repository.DashboardRepository;
import com.skillops.domain10_analytics.repository.UserAnalyticsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/analytics-domain")
@Tag(name = OpenApiConfig.DOMAIN_10)
public class Domain10AnalyticsController {

    private final CrudFacade crud;
    private final DashboardRepository dashboardRepository;
    private final UserAnalyticsRepository userAnalyticsRepository;

    public Domain10AnalyticsController(
            CrudFacade crud,
            DashboardRepository dashboardRepository,
            UserAnalyticsRepository userAnalyticsRepository
    ) {
        this.crud = crud;
        this.dashboardRepository = dashboardRepository;
        this.userAnalyticsRepository = userAnalyticsRepository;
    }

    @GetMapping("/summary")
    @Operation(summary = "GET domain row counts")
    public ResponseEntity<Map<String, Object>> summary() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("domain", OpenApiConfig.DOMAIN_10);
        body.put("dashboards", dashboardRepository.count());
        body.put("user-analytics", userAnalyticsRepository.count());
        return ResponseEntity.ok(body);
    }

    @GetMapping("/dashboards")
    @Operation(summary = "GET all Dashboard records")
    public List<Dashboard> listDashboard() {
        return crud.findAll(dashboardRepository);
    }

    @GetMapping("/dashboards/{id}")
    @Operation(summary = "GET Dashboard by id")
    public Dashboard getDashboard(@PathVariable Long id) {
        return crud.get(dashboardRepository, id, "Dashboard");
    }

    @PostMapping("/dashboards")
    @Operation(summary = "POST create Dashboard")
    public ResponseEntity<Dashboard> createDashboard(@Valid @RequestBody Dashboard body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(dashboardRepository, body));
    }

    @PutMapping("/dashboards/{id}")
    @Operation(summary = "PUT replace Dashboard")
    public Dashboard replaceDashboard(@PathVariable Long id, @Valid @RequestBody Dashboard body) {
        return crud.replace(dashboardRepository, id, body, "Dashboard");
    }

    @PatchMapping("/dashboards/{id}")
    @Operation(summary = "PATCH Dashboard")
    public Dashboard patchDashboard(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(dashboardRepository, id, updates, "Dashboard");
    }

    @DeleteMapping("/dashboards/{id}")
    @Operation(summary = "DELETE Dashboard")
    public ResponseEntity<Void> deleteDashboard(@PathVariable Long id) {
        crud.delete(dashboardRepository, id, "Dashboard");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user-analytics")
    @Operation(summary = "GET all UserAnalytics records")
    public List<UserAnalytics> listUserAnalytics() {
        return crud.findAll(userAnalyticsRepository);
    }

    @GetMapping("/user-analytics/{id}")
    @Operation(summary = "GET UserAnalytics by id")
    public UserAnalytics getUserAnalytics(@PathVariable Long id) {
        return crud.get(userAnalyticsRepository, id, "UserAnalytics");
    }

    @PostMapping("/user-analytics")
    @Operation(summary = "POST create UserAnalytics")
    public ResponseEntity<UserAnalytics> createUserAnalytics(@Valid @RequestBody UserAnalytics body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crud.create(userAnalyticsRepository, body));
    }

    @PutMapping("/user-analytics/{id}")
    @Operation(summary = "PUT replace UserAnalytics")
    public UserAnalytics replaceUserAnalytics(@PathVariable Long id, @Valid @RequestBody UserAnalytics body) {
        return crud.replace(userAnalyticsRepository, id, body, "UserAnalytics");
    }

    @PatchMapping("/user-analytics/{id}")
    @Operation(summary = "PATCH UserAnalytics")
    public UserAnalytics patchUserAnalytics(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return crud.patch(userAnalyticsRepository, id, updates, "UserAnalytics");
    }

    @DeleteMapping("/user-analytics/{id}")
    @Operation(summary = "DELETE UserAnalytics")
    public ResponseEntity<Void> deleteUserAnalytics(@PathVariable Long id) {
        crud.delete(userAnalyticsRepository, id, "UserAnalytics");
        return ResponseEntity.noContent().build();
    }

}
