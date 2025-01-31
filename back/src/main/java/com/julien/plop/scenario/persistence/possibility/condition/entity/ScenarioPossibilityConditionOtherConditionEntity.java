package com.julien.plop.scenario.persistence.possibility.condition.entity;


import com.julien.plop.scenario.PossibilityCondition;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OTHER_CONDITION")
public final class ScenarioPossibilityConditionOtherConditionEntity
        extends ScenarioPossibilityConditionAbstractEntity {

    @Column(name = "other_condition_id")
    private String otherConditionId;

    public String getOtherConditionId() {
        return otherConditionId;
    }

    public void setOtherConditionId(String otherConditionId) {
        this.otherConditionId = otherConditionId;
    }

    public PossibilityCondition toModel() {
        return new PossibilityCondition.
                OtherCondition(new PossibilityCondition.Id(id), new PossibilityCondition.Id(otherConditionId));
    }
}
