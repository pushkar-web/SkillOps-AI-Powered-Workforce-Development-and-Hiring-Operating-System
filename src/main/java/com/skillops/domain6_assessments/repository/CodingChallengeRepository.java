package com.skillops.domain6_assessments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain6_assessments.entity.CodingChallenge;

public interface CodingChallengeRepository extends JpaRepository<CodingChallenge, Long> {
}
