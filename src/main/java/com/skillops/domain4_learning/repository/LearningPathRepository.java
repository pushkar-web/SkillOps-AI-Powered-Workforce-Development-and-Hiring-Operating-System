package com.skillops.domain4_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain4_learning.entity.LearningPath;

public interface LearningPathRepository extends JpaRepository<LearningPath, Long> {
}
