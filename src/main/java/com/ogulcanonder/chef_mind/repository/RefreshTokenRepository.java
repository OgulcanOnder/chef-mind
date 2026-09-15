package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken r WHERE r.email=:email ")
    void deleteRefreshTokenByEmail(String email);
}
