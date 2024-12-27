package com.julien.plop.board.presenter.dto;

import com.julien.plop.board.domain.BoardPoint;
import com.julien.plop.board.persistence.BoardPointEntity;

public record BoardPointDTO(double lat, double lng) {

    public static BoardPointDTO fromEntity(BoardPointEntity entity) {
        return new BoardPointDTO(entity.getLat(), entity.getLng());
    }

    public BoardPointEntity toEntity() {
        BoardPointEntity entity = new BoardPointEntity();
        entity.setLat(lat);
        entity.setLng(lng);
        return entity;
    }

    public BoardPoint toModel() {
        return new BoardPoint(lat, lng);
    }
}
