package com.skillops.domain10_analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain10_analytics.entity.UserAnalytics;

public interface UserAnalyticsRepository extends JpaRepository<UserAnalytics, Long> {
}
