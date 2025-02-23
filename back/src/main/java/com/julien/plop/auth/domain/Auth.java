package com.julien.plop.auth.domain;

import com.julien.plop.player.domain.model.Player;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public record Auth(Id id, Instant dateTime, String deviceId, Optional<Player> optPlayer) {

    public record Id(String value) {

    }

    public boolean isExpiry() {
        return dateTime.isAfter(Instant.now().plus(Duration.ofHours(1)));
    }

    public Player player() throws AuthException {
        return optPlayer.orElseThrow(() -> new AuthException(AuthException.Type.ANONYMOUS));
    }

    public Auth withPlayer(Player player) {
        return new Auth(id, dateTime, deviceId, Optional.of(player));
    }

}

