package com.bakcell.nomre.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Javidan Alizada
 */
public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException() {
        this("Category not found", HttpStatus.NOT_FOUND);
    }

    public CategoryNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}