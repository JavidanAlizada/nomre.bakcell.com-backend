package com.bakcell.nomre.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Javidan Alizada
 */
public class BadMSISDNException extends BaseException {
    public BadMSISDNException() {
        this("MSISDN is not correct", HttpStatus.BAD_REQUEST);
    }

    public BadMSISDNException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
