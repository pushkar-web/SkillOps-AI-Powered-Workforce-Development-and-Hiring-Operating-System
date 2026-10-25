package com.skillops.domain2_user_profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain2_user_profiles.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
