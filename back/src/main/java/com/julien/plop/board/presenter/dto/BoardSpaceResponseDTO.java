package com.julien.plop.board.presenter.dto;

import com.julien.plop.board.persistence.BoardSpaceEntity;

public record BoardSpaceResponseDTO(String id, String label) {

    public static BoardSpaceResponseDTO fromEntity(BoardSpaceEntity entity) {
        return new BoardSpaceResponseDTO(entity.getId(), entity.getLabel());
    }

}
