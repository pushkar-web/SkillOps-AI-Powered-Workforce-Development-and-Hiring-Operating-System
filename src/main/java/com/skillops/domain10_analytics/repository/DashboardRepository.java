package com.skillops.domain10_analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain10_analytics.entity.Dashboard;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
}
