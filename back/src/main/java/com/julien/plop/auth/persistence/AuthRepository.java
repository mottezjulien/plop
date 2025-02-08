package com.julien.plop.auth.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, String> {

    @Query("FROM AuthEntity auth" +
            " LEFT JOIN FETCH auth.player player" +
            " WHERE auth.token = :token")
    Optional<AuthEntity> findByTokenFetchPlayer(String token);

}
