package com.julien.plop.scenario.persistence.possibility.trigger.entity;


import com.julien.plop.scenario.PossibilityTrigger;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "TEST1_SCENARIO_POSSIBILITY_ACTION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class ScenarioPossibilityTriggerAbstractEntity {

    @Id
    @UuidGenerator
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
}
