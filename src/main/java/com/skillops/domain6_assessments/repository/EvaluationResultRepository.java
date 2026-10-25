package com.skillops.domain6_assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain6_assessments.entity.EvaluationResult;

public interface EvaluationResultRepository extends JpaRepository<EvaluationResult, Long> {
}
