package com.skillops.domain4_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain4_learning.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
