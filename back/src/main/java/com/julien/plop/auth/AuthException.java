package com.julien.plop.auth;

public class AuthException extends Exception {

    public enum Type {
        EMPTY,
        ANONYMOUS,
        EXPIRED_TOKEN
    }

    private final Type type;

    public AuthException(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
