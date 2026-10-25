package com.skillops.domain8_recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain8_recruitment.entity.CandidateMatch;

public interface CandidateMatchRepository extends JpaRepository<CandidateMatch, Long> {
}
