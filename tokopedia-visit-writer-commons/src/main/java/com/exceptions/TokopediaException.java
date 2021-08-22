package com.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokopediaException extends RuntimeException {

    private final HttpStatus httpStatusCode;
    private final String message;

    public TokopediaException(String message, HttpStatus httpStatusCode, Throwable throwable) {
        super(message, throwable);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public TokopediaException(String message, HttpStatus httpStatusCode) {
        super(message);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
