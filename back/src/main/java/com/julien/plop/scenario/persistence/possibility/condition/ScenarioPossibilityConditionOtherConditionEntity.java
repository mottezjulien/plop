package com.julien.plop.scenario.persistence.possibility.condition;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("OTHER_CONDITION")
public final class ScenarioPossibilityConditionOtherConditionEntity
        extends ScenarioPossibilityConditionAbstractEntity {

    @ManyToOne
    @JoinColumn(name = "other_condition_id")
    private ScenarioPossibilityConditionAbstractEntity otherCondition;

    public ScenarioPossibilityConditionAbstractEntity getOtherCondition() {
        return otherCondition;
    }

    public void setOtherCondition(ScenarioPossibilityConditionAbstractEntity otherCondition) {
        this.otherCondition = otherCondition;
    }

}
