package com.julien.plop.game;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<GameEntity, String> {

    Optional<GameEntity> findByCode(String code);

}
