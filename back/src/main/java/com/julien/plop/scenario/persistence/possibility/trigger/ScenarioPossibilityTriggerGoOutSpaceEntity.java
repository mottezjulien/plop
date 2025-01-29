package com.julien.plop.scenario.persistence.possibility.trigger;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "GO_OUT_SPACE")
public final class ScenarioPossibilityTriggerGoOutSpaceEntity extends ScenarioPossibilityTriggerAbstractEntity {

    @Column(name = "go_out_space_id")
    private String spaceId;

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

}
