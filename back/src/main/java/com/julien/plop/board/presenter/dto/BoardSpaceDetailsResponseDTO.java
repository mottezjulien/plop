package com.julien.plop.board.presenter.dto;

import com.julien.plop.board.persistence.BoardSpaceEntity;

import java.util.List;

public record BoardSpaceDetailsResponseDTO(String id, String label, int priority, List<BoardRectResponseDTO> rects) {
    public static BoardSpaceDetailsResponseDTO fromEntity(BoardSpaceEntity entity) {
        List<BoardRectResponseDTO> rects = entity.getRects().stream()
                .map(BoardRectResponseDTO::fromEntity)
                .toList();
        return new BoardSpaceDetailsResponseDTO(entity.getId(), entity.getLabel(), entity.getPriority(), rects);
    }
}
