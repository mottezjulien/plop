package com.julien.plop.scenario.persistence.possibility.condition.entity;

import com.julien.plop.scenario.PossibilityCondition;
import jakarta.persistence.*;

@Entity
@Table(name = "TEST1_SCENARIO_POSSIBILITY_CONDITION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class ScenarioPossibilityConditionAbstractEntity {

    @Id
    //@UuidGenerator
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
        };
    }

    public static ScenarioPossibilityConditionAbstractEntity fromModel(PossibilityCondition condition) {
        return switch (condition) {
            case PossibilityCondition.AbsoluteTime absoluteTime -> {
                ScenarioPossibilityConditionAbsoluteTimeEntity entity = new ScenarioPossibilityConditionAbsoluteTimeEntity();
                entity.setId(absoluteTime.id().value());
                entity.setMinutes((int) absoluteTime.duration().toMinutes());
                yield entity;
            }
            case PossibilityCondition.RelativeTimeAfterOtherTrigger relativeTimeAfterOtherTrigger -> {
                ScenarioPossibilityConditionRelativeTimeAfterOtherTriggerEntity entity = new ScenarioPossibilityConditionRelativeTimeAfterOtherTriggerEntity();
                entity.setId(relativeTimeAfterOtherTrigger.id().value());
                entity.setMinutes((int) relativeTimeAfterOtherTrigger.duration().toMinutes());
                entity.setOtherTriggerId(relativeTimeAfterOtherTrigger.otherTriggerId().value());
                yield entity;
            }
            case PossibilityCondition.InStep inStep -> {
                ScenarioPossibilityConditionInStepEntity entity = new ScenarioPossibilityConditionInStepEntity();
                entity.setId(inStep.id().value());
                entity.setStepId(inStep.stepId().value());
                yield entity;
            }
            case PossibilityCondition.InsideSpace insideSpace -> {
                ScenarioPossibilityConditionInsideSpaceEntity entity = new ScenarioPossibilityConditionInsideSpaceEntity();
                entity.setId(insideSpace.id().value());
                entity.setSpaceId(insideSpace.spaceId().value());
                yield entity;
            }
            case PossibilityCondition.OutsideSpace outsideSpace -> {
                ScenarioPossibilityConditionOutsideSpaceEntity entity = new ScenarioPossibilityConditionOutsideSpaceEntity();
                entity.setId(outsideSpace.id().value());
                entity.setSpaceId(outsideSpace.spaceId().value());
                yield entity;
            }
            case PossibilityCondition.OtherCondition otherCondition -> {
                ScenarioPossibilityConditionOtherConditionEntity entity = new ScenarioPossibilityConditionOtherConditionEntity();
                entity.setId(otherCondition.id().value());
                entity.setOtherConditionId(otherCondition.otherConditionId().value());
                yield entity;
            }
        };
    }


}
