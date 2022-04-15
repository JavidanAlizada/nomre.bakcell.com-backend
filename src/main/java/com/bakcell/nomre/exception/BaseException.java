package com.bakcell.nomre.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author Javidan Alizada
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus httpStatus;
    private Date date;

    public BaseException() {
    }

    public BaseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.date = new Date();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", date=" + date +
                '}';
    }
}
