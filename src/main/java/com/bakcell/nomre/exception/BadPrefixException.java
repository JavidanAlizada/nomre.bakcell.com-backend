package com.bakcell.nomre.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Javidan Alizada
 */
public class BadPrefixException extends BaseException {
    public BadPrefixException() {
        this("Prefix is not correct", HttpStatus.BAD_REQUEST);
    }

    public BadPrefixException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
