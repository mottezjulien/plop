package com.julien.plop.board.persistence.repository;

import com.julien.plop.board.persistence.entity.BoardRectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRectRepository extends JpaRepository<BoardRectEntity, String> {
}
