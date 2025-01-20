package com.julien.plop.board.presenter.dto;

import com.julien.plop.board.persistence.entity.BoardEntity;

public record BoardResponseDTO(String id) {

    public static BoardResponseDTO fromEntity(BoardEntity entity) {
        return new BoardResponseDTO(entity.getId());
    }

}
