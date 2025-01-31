package com.julien.plop.scenario.persistence.possibility.condition.entity;


import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.scenario.PossibilityCondition;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OUTSIDE_SPACE")
public final class ScenarioPossibilityConditionOutsideSpaceEntity
        extends ScenarioPossibilityConditionAbstractEntity {

    @Column(name = "outside_space_id")
    private String spaceId;

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public PossibilityCondition toModel() {
        return new PossibilityCondition.OutsideSpace(new PossibilityCondition.Id(id), new BoardSpace.Id(spaceId));
    }
}
