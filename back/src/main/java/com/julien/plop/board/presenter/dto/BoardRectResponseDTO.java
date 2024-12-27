package com.julien.plop.board.presenter.dto;

import com.julien.plop.board.persistence.BoardRectEntity;

public record BoardRectResponseDTO(BoardPointDTO bottomLeft, BoardPointDTO topRight) {
    public static BoardRectResponseDTO fromEntity(BoardRectEntity entity) {
        return new BoardRectResponseDTO(BoardPointDTO.fromEntity(entity.getBottomLeft()),
                BoardPointDTO.fromEntity(entity.getTopRight()));
    }

}
