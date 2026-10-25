package com.skillops.domain1_identity_authentication.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain1_identity_authentication.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}
