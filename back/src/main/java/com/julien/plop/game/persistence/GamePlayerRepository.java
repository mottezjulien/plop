package com.julien.plop.game.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface GamePlayerRepository extends JpaRepository<GamePlayerEntity, GamePlayerEntity.GamePlayerId> {

    @Query("FROM GamePlayerEntity game_player" +
            " LEFT JOIN FETCH game_player.player player" +
            " LEFT JOIN FETCH game_player.actions action" +
            " WHERE game_player.id = :id")
    Optional<GamePlayerEntity> findByIdFetchPlayer(@Param("id") GamePlayerEntity.GamePlayerId id);

}
