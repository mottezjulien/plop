package com.julien.plop.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<BoardEntity, String> {

}
