package com.julien.plop.scenario.persistence.possibility.condition;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INSIDE_SPACE")
public final class ScenarioPossibilityConditionInsideSpaceEntity
        extends ScenarioPossibilityConditionAbstractEntity {

    @Column(name = "inside_space_id")
    private String spaceId;

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

}
