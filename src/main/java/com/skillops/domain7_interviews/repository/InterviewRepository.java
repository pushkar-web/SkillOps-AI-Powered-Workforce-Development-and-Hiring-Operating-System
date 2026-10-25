package com.skillops.domain7_interviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain7_interviews.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
