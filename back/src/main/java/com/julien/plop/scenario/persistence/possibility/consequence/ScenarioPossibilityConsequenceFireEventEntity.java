package com.julien.plop.scenario.persistence.possibility.consequence;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FIRE_EVENT")
public final class ScenarioPossibilityConsequenceFireEventEntity
    extends ScenarioPossibilityConsequenceAbstractEntity {

}
