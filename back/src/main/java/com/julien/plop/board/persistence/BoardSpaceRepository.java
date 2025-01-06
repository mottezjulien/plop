package com.julien.plop.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardSpaceRepository extends JpaRepository<BoardSpaceEntity, String> {

    /*@Query("FROM BoardSpaceEntity space" +
            " LEFT JOIN FETCH space.rects rect"+
            " LEFT JOIN FETCH rect.bottomLeft bottomLeft"+
            " LEFT JOIN FETCH rect.topRight topRight"+
            " WHERE space.id = :spaceId")
    Optional<BoardSpaceEntity> findByIdFetchs(@Param("spaceId") String spaceId);

    @Query("FROM BoardSpaceEntity sp" +
            " WHERE sp.board.id = :boardId")
    List<BoardSpaceEntity> findByBoardId(@Param("boardId") String boardId);*/

}
