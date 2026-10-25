package com.skillops.domain2_user_profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain2_user_profiles.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
