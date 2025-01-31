package com.julien.plop.game.domain;

public class GameException extends Exception {

    public enum Type {
        NOT_FOUND
    }

    private final Type type;

    public GameException(Type type) {
        this.type = type;
    }

    public Type type() {
        return type;
    }
}
