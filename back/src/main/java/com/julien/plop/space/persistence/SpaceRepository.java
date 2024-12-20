package com.julien.plop.space.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpaceRepository extends JpaRepository<SpaceEntity, String> {

    String DEFAULT_QUERY = "FROM SpaceEntity sp" +
            " LEFT JOIN FETCH sp.points po";

    @Query(DEFAULT_QUERY +
            " WHERE sp.label = :label")
    Optional<SpaceEntity> findByLabel(@Param("label") String label);

    @Query(DEFAULT_QUERY)
    List<SpaceEntity> findAll();

}
