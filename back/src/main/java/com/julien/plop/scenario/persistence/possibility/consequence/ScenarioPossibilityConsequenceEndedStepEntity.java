package com.julien.plop.scenario.persistence.possibility.consequence;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ENDED_STEP")
public final class ScenarioPossibilityConsequenceEndedStepEntity extends
    ScenarioPossibilityConsequenceAbstractEntity {

    @Column(name = "ended_step_id")
    private String stepId;

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

}
