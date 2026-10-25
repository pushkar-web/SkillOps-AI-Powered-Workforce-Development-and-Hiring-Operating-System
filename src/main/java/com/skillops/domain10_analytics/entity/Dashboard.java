package com.skillops.domain10_analytics.entity;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dashboards", schema = "analytics")
public class Dashboard {

    public Dashboard() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "profile_id", nullable = false)
    private Long profileId;

    @NotBlank
    @Column(name = "dashboard_type", nullable = false)
    private String dashboardType;

    @NotBlank
    @Column(name = "dashboard_name", nullable = false)
    private String dashboardName;

    @NotNull
    @Column(name = "widgets_count", nullable = false)
    private Integer widgetsCount;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @Column(name = "last_refreshed")
    private OffsetDateTime lastRefreshed;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
        if (isDefault == null) {
            isDefault = false;
        }
    }


    @OneToMany(mappedBy = "dashboard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserAnalytics> userAnalyticsList = new ArrayList<>();
}
