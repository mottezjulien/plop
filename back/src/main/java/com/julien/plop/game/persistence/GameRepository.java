package com.julien.plop.game.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameRepository extends JpaRepository<GameEntity, String> {

    @Query("FROM GameEntity game" +
            " LEFT JOIN FETCH game.scenario" +
            " LEFT JOIN FETCH game.board" +
            " LEFT JOIN FETCH game.players player" +
            " WHERE game.id = :gameId")
    Optional<GameEntity> findByIdFetchAll(@Param("gameId") String gameId);

    /*
    @Query("SELECT count(game) > 0" +
            " FROM GameEntity game" +
            " LEFT JOIN game.players player" +
            " WHERE game.id = :gameId AND player.id = :playerId")
    boolean isIn(@Param("gameId") String gameId, String playerId);*/

    @Query("SELECT count(game) > 0" +
            " FROM GameEntity game" +
            " LEFT JOIN game.players player" +
            " WHERE game.id = :gameId AND game.state = 'STARTED'")
    boolean isStarted(String value);
}
