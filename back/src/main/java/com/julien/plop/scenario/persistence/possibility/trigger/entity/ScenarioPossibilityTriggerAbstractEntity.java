package com.julien.plop.scenario.persistence.possibility.trigger.entity;


import com.julien.plop.scenario.PossibilityTrigger;
import jakarta.persistence.*;

@Entity
@Table(name = "TEST1_SCENARIO_POSSIBILITY_ACTION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class ScenarioPossibilityTriggerAbstractEntity {

    @Id
    //@UuidGenerator
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PossibilityTrigger abstractToModel() {
        return switch (this) {
            case ScenarioPossibilityTriggerAbsoluteTimeEntity absoluteTime -> absoluteTime.toModel();
            case ScenarioPossibilityTriggerRelativeTimeAfterOtherTriggerEntity relativeTime -> relativeTime.toModel();
            case ScenarioPossibilityTriggerGoInSpaceEntity goInSpace -> goInSpace.toModel();
            case ScenarioPossibilityTriggerGoOutSpaceEntity goOutSpace -> goOutSpace.toModel();
            default -> throw new IllegalStateException("Unknown type");
        };
    }

    public static ScenarioPossibilityTriggerAbstractEntity fromModel_emptyId(PossibilityTrigger model) {
        return switch (model) {
            case PossibilityTrigger.AbsoluteTime absoluteTime -> {
                ScenarioPossibilityTriggerAbsoluteTimeEntity entity = new ScenarioPossibilityTriggerAbsoluteTimeEntity();
                entity.setMinutes((int) absoluteTime.duration().toMinutes());
                yield entity;
            }
            case PossibilityTrigger.RelativeTimeAfterOtherTrigger relativeTimeAfterOtherTrigger -> {
                ScenarioPossibilityTriggerRelativeTimeAfterOtherTriggerEntity entity = new ScenarioPossibilityTriggerRelativeTimeAfterOtherTriggerEntity();
                entity.setMinutes((int) relativeTimeAfterOtherTrigger.duration().toMinutes());
                entity.setOtherTriggerId(relativeTimeAfterOtherTrigger.otherTriggerId().value());
                yield entity;
            }
            case PossibilityTrigger.GoInSpace goInSpace -> {
                ScenarioPossibilityTriggerGoInSpaceEntity entity = new ScenarioPossibilityTriggerGoInSpaceEntity();
                entity.setSpaceId(goInSpace.spaceId().value());
                yield entity;
            }
            case PossibilityTrigger.GoOutSpace goOutSpace -> {
                ScenarioPossibilityTriggerGoInSpaceEntity entity = new ScenarioPossibilityTriggerGoInSpaceEntity();
                entity.setSpaceId(goOutSpace.spaceId().value());
                yield entity;
            }
        };
    }

}
