package com.julien.plop.game.domain;

public class GameException extends Exception {

    public enum Type {
        PLAYER_NOT_IN, GAME_NOT_STARTED, TEAMPLATE_NOT_FOUND, GAME_NOT_FOUND
    }

    private final Type type;

    public GameException(Type type) {
        this.type = type;
    }

    public Type type() {
        return type;
    }
}
