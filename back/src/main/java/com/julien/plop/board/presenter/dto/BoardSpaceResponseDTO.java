package com.julien.plop.board.presenter.dto;

import com.julien.plop.board.domain.BoardSpace;
import com.julien.plop.board.persistence.BoardSpaceEntity;

public record BoardSpaceResponseDTO(String id, String label, int priority) {
    public static BoardSpaceResponseDTO fromEntity(BoardSpaceEntity entity) {
        return new BoardSpaceResponseDTO(entity.getId(), entity.getLabel(), entity.getPriority());
    }

    public static BoardSpaceResponseDTO fromModel(BoardSpace model) {
        return new BoardSpaceResponseDTO(model.id().value(), model.label(), model.priority());
    }
}
