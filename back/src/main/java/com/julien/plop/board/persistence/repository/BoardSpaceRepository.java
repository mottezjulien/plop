package com.julien.plop.board.persistence.repository;

import com.julien.plop.board.persistence.entity.BoardSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardSpaceRepository extends JpaRepository<BoardSpaceEntity, String> {

}
