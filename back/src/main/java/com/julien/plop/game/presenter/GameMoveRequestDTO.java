package com.julien.plop.game.presenter;

import com.julien.plop.board.model.BoardSpace;

public record GameMoveRequestDTO(Float lat, Float lng) {

    public BoardSpace.Point toModel() {
        return new BoardSpace.Point(lat, lng);
    }

}
