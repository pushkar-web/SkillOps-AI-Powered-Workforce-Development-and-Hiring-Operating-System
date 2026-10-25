package com.skillops.domain2_user_profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain2_user_profiles.entity.SkillPassport;

public interface SkillPassportRepository extends JpaRepository<SkillPassport, Long> {
}
