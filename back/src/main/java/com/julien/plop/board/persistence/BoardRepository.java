package com.julien.plop.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    Optional<BoardEntity> findByGameId(String gameId);

}
