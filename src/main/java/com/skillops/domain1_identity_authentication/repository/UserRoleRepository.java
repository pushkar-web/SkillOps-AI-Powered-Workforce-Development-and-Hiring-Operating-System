package com.skillops.domain1_identity_authentication.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain1_identity_authentication.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserId(Long userId);
}
