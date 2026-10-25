package com.skillops.domain1_identity_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain1_identity_authentication.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
