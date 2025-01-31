package com.julien.plop.scenario.persistence.possibility.condition.entity;

import com.julien.plop.scenario.PossibilityCondition;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "TEST1_SCENARIO_POSSIBILITY_CONDITION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class ScenarioPossibilityConditionAbstractEntity {

    @Id
    @UuidGenerator
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PossibilityCondition abstractToModel() {
        return switch (this) {
            case ScenarioPossibilityConditionInsideSpaceEntity insideSpace -> insideSpace.toModel();
            case ScenarioPossibilityConditionOutsideSpaceEntity outsideSpace -> outsideSpace.toModel();
            case ScenarioPossibilityConditionAbsoluteTimeEntity absoluteTime -> absoluteTime.toModel();
            case ScenarioPossibilityConditionRelativeTimeAfterOtherTriggerEntity relativeTime -> relativeTime.toModel();
            case ScenarioPossibilityConditionInStepEntity inStep -> inStep.toModel();
            case ScenarioPossibilityConditionOtherConditionEntity otherCondition -> otherCondition.toModel();
            default -> throw new IllegalStateException("Unknown type");

            /*


        PossibilityCondition.InStep,
        PossibilityCondition.OtherCondition
             */
        };
    }
}
