package com.julien.plop.game.presenter;

import com.julien.plop.game.domain.Game;

public record GameResponseDTO(String id, String label) {

    public static GameResponseDTO fromModel(Game game) {
        return new GameResponseDTO(
                game.id().value(),
                game.label()
        );
    }

}
