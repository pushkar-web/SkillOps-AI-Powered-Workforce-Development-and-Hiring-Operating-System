package com.skillops.domain2_user_profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain2_user_profiles.entity.CareerGoal;

public interface CareerGoalRepository extends JpaRepository<CareerGoal, Long> {
}
