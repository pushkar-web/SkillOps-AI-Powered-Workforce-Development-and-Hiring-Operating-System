package com.skillops.domain3_skills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain3_skills.entity.SkillCategory;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {
}
