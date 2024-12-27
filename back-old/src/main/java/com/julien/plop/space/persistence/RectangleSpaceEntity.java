package com.julien.plop.board.persistence;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "rectangle")
public class RectangleSpaceEntity extends AbstractSpaceEntity {

    @ManyToOne
    @JoinColumn(name = "bottom_left_point_id")
    private SpacePointEntity bottomLeft;

    @ManyToOne
    @JoinColumn(name = "top_right_point_id")
    private SpacePointEntity topRight;

    public SpacePointEntity getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(SpacePointEntity bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public SpacePointEntity getTopRight() {
        return topRight;
    }

    public void setTopRight(SpacePointEntity topRight) {
        this.topRight = topRight;
    }

}
