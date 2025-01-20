package com.julien.plop.first.game.presenter;

import com.julien.plop.first.game.persistence.FirstGameEntity;

public record GameResponseDTO(String id, String label, String state) {

    public static GameResponseDTO fromEntity(FirstGameEntity entity) {
        return new GameResponseDTO(entity.getId(), entity.getLabel(), entity.getState().name());
    }

}
