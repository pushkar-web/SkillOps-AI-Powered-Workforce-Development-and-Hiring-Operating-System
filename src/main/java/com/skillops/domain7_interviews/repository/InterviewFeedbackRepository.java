package com.skillops.domain7_interviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain7_interviews.entity.InterviewFeedback;

public interface InterviewFeedbackRepository extends JpaRepository<InterviewFeedback, Long> {
}
