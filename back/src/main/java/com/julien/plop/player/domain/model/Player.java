package com.julien.plop.player.domain.model;

import com.julien.plop.i18n.domain.Language;
import com.julien.plop.tools.StringTools;

import java.util.Objects;

public record Player(Id id, String name, Language language) {

    public record Id(String value) {
        public Id() {
            this(StringTools.generate());
        }
    }

    public Player(String name) {
        this(new Id(), name, Language.first());
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
