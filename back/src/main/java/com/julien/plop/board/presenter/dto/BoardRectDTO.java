package com.julien.plop.board.presenter.dto;

import com.julien.plop.board.persistence.BoardRectEntity;

public record BoardRectDTO(BoardPointDTO bottomLeft, BoardPointDTO topRight) {

    public BoardRectEntity toEntity() {
        BoardRectEntity entity = new BoardRectEntity();
        entity.setBottomLeft(bottomLeft.toEntity());
        entity.setTopRight(topRight.toEntity());
        return entity;
    }

}
