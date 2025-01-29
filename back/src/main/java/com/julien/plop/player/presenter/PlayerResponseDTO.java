package com.julien.plop.player.presenter;

import com.julien.plop.player.persistence.PlayerEntity;

public record PlayerResponseDTO(String id, String name, String deviceId) {

    public static PlayerResponseDTO fromEntity(PlayerEntity entity) {
        return new PlayerResponseDTO(entity.getId(), entity.getName(), entity.getDeviceId());
    }
}
