package com.skillops.domain6_assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain6_assessments.entity.CodingSubmission;

public interface CodingSubmissionRepository extends JpaRepository<CodingSubmission, Long> {
}
