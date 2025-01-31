package com.julien.plop.scenario.persistence.possibility.trigger.entity;

import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.scenario.PossibilityTrigger;
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

    public PossibilityTrigger toModel() {
        return new PossibilityTrigger.GoOutSpace(new PossibilityTrigger.Id(id), new BoardSpace.Id(spaceId));
    }

}
