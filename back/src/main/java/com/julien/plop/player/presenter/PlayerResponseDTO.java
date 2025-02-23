package com.julien.plop.player.presenter;

import com.julien.plop.player.domain.model.Player;
import com.julien.plop.player.persistence.PlayerEntity;

public record PlayerResponseDTO(String id, String name, String language) {

    public static PlayerResponseDTO fromEntity(PlayerEntity entity) {
        return new PlayerResponseDTO(entity.getId(), entity.getName(), entity.getLanguage().name());
    }

    public static PlayerResponseDTO fromModel(Player player) {
        return new PlayerResponseDTO(player.id().value(), player.name(), player.language().name());
    }

}
