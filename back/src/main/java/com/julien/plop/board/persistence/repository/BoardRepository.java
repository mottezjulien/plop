package com.julien.plop.board.persistence.repository;

import com.julien.plop.board.persistence.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    @Query("FROM BoardEntity board" +
            " LEFT JOIN board.games game" +
            " LEFT JOIN FETCH board.spaces space" +
            " LEFT JOIN FETCH space.rects rect" +
            " WHERE game.id = :gameId")
    Optional<BoardEntity> findByGameIdFetchSpacesAndRects(@Param("gameId") String gameId);

}
