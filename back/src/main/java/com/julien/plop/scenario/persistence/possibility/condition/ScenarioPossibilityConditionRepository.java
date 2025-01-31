package com.julien.plop.scenario.persistence.possibility.condition;

import com.julien.plop.scenario.persistence.possibility.condition.entity.ScenarioPossibilityConditionAbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioPossibilityConditionRepository extends JpaRepository<ScenarioPossibilityConditionAbstractEntity, String> {


}
