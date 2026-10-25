package com.skillops.domain6_assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain6_assessments.entity.AssessmentFeedback;

public interface AssessmentFeedbackRepository extends JpaRepository<AssessmentFeedback, Long> {
}
