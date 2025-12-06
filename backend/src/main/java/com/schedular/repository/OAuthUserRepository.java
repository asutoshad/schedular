package com.schedular.repository;

import com.schedular.model.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {
    Optional<OAuthUser> findByEmail(String email);
}
