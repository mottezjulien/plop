package com.julien.plop.board.persistence.repository;

import com.julien.plop.board.persistence.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {

}
