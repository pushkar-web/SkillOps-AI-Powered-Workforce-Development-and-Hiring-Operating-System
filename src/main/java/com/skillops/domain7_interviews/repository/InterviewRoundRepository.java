package com.skillops.domain7_interviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain7_interviews.entity.InterviewRound;

public interface InterviewRoundRepository extends JpaRepository<InterviewRound, Long> {
}
