package com.julien.plop.game.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayerActionRepository extends JpaRepository<GamePlayerActionEntity, String> {

}
