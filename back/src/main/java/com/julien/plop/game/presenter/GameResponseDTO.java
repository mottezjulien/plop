package com.julien.plop.game.presenter;

import com.julien.plop.game.persistence.GameEntity;

public record GameResponseDTO(String id, String label, String state) {

    public static GameResponseDTO fromEntity(GameEntity entity) {
        return new GameResponseDTO(entity.getId(), entity.getLabel(), entity.getState().name());
    }

}
