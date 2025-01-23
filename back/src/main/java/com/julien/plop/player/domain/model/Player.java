package com.julien.plop.player.domain.model;

import java.util.Objects;

public record Player(Id id, String name) {

    public record Id(String value) {

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player player)) return false;

        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
