package com.skillops.domain1_identity_authentication.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain1_identity_authentication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
