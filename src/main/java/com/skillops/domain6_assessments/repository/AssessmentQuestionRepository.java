package com.skillops.domain6_assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain6_assessments.entity.AssessmentQuestion;

public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion, Long> {
}
