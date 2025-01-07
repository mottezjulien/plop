package com.julien.plop.board.persistence.repository;

import com.julien.plop.board.persistence.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    Optional<BoardEntity> findByGameId(String gameId);

    @Query("SELECT b FROM BoardEntity b LEFT JOIN FETCH b.spaces WHERE b.id = :boardId")
    Optional<BoardEntity> findByIdFetchSpaces(@Param("boardId") String boardId);

}
