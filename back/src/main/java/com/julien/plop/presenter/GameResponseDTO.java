package com.julien.plop.presenter;

import com.julien.plop.game.Game;

public record GameResponseDTO(String id, String code, String label) {

    public static GameResponseDTO fromModel(Game game) {
        return new GameResponseDTO(
                game.id().value(),
                game.code(),
                game.label()
        );
    }

}
