package com.julien.plop.board.domain;

import java.util.Objects;

public record BoardSpaceId(String value) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardSpaceId userId = (BoardSpaceId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}