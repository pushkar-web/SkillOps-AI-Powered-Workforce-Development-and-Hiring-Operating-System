package com.skillops.domain1_identity_authentication.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain1_identity_authentication.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
