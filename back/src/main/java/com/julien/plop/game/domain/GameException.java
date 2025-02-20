package com.julien.plop.game.domain;

public class GameException extends Exception {

    public enum Type {
        TEMPLATE_NOT_FOUND, GAME_NOT_FOUND, GAME_NOT_STARTED,
        PLAYER_NOT_IN_GAME, PLAYER_NOT_SET
    }

    private final Type type;

    public GameException(Type type) {
        this.type = type;
    }

    public Type type() {
        return type;
    }
}
