package com.julien.plop.scenario.persistence.possibility.consequence.entity;


import com.julien.plop.scenario.PossibilityConsequence;
import com.julien.plop.scenario.Scenario;
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

    public PossibilityConsequence toModel() {
        return new PossibilityConsequence.EndedStep(new PossibilityConsequence.Id(id), new Scenario.Step.Id(stepId));
    }

}
