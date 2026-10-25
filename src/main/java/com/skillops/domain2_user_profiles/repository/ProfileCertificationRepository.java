package com.skillops.domain2_user_profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain2_user_profiles.entity.ProfileCertification;

public interface ProfileCertificationRepository extends JpaRepository<ProfileCertification, Long> {
}
