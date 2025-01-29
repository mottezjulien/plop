package com.julien.plop.board.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "TEST1_BOARD_RECT")
public class BoardRectEntity {

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private BoardSpaceEntity space;

    @ManyToOne
    @JoinColumn(name = "bottom_left_point_id")
    private BoardPointEntity bottomLeft;

    @ManyToOne
    @JoinColumn(name = "top_right_point_id")
    private BoardPointEntity topRight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BoardSpaceEntity getSpace() {
        return space;
    }

    public void setSpace(BoardSpaceEntity space) {
        this.space = space;
    }

    public BoardPointEntity getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(BoardPointEntity bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public BoardPointEntity getTopRight() {
        return topRight;
    }

    public void setTopRight(BoardPointEntity topRight) {
        this.topRight = topRight;
    }

}
