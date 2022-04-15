package com.bakcell.nomre.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Javidan Alizada
 */
public class CsvFileNotFoundException extends BaseException {
    public CsvFileNotFoundException() {
        this("Csv file not found on the resources", HttpStatus.NOT_FOUND);
    }

    public CsvFileNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}