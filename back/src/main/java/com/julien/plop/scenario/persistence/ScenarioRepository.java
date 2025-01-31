package com.julien.plop.scenario.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<ScenarioEntity, String> {

}
