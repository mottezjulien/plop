package com.julien.plop.scenario.persistence.possibility.condition.entity;


import com.julien.plop.scenario.PossibilityCondition;
import com.julien.plop.scenario.Scenario;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("IN_STEP")
public final class ScenarioPossibilityConditionInStepEntity
        extends ScenarioPossibilityConditionAbstractEntity {

    @Column(name = "in_step_id")
    private String stepId;

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public PossibilityCondition toModel() {
        return new PossibilityCondition.InStep(new PossibilityCondition.Id(id), new Scenario.Step.Id(stepId));
    }
}
