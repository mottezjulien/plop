package com.julien.plop.generic.presenter.exception;

public abstract class HttpException extends Exception {

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

}
