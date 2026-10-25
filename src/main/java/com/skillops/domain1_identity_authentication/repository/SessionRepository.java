package com.skillops.domain1_identity_authentication.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain1_identity_authentication.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUserId(Long userId);
}
