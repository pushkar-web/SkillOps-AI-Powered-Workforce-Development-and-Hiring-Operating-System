package com.skillops.domain4_learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain4_learning.entity.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
}
