package com.julien.plop.generic.presenter.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestHttpException extends HttpException {


    public BadRequestHttpException(String message) {
        super(message);
    }
}
