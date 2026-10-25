package com.skillops.domain6_assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain6_assessments.entity.AssessmentAttempt;

public interface AssessmentAttemptRepository extends JpaRepository<AssessmentAttempt, Long> {
}
