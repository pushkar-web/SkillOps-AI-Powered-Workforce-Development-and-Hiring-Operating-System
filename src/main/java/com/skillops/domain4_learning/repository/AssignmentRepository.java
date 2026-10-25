package com.skillops.domain4_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain4_learning.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
