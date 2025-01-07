package com.julien.plop.board.persistence.repository;

import com.julien.plop.board.persistence.entity.BoardPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPointRepository extends JpaRepository<BoardPointEntity, String> {

}
