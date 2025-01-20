package com.julien.plop.first.game.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstGameRepository extends JpaRepository<FirstGameEntity, String> {

}
