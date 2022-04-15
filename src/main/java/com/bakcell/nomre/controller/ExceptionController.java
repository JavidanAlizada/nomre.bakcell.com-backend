package com.bakcell.nomre.controller;

import com.bakcell.nomre.exception.BadMSISDNException;
import com.bakcell.nomre.exception.BadPrefixException;
import com.bakcell.nomre.exception.CategoryNotFoundException;
import com.bakcell.nomre.exception.CsvFileNotFoundException;
import com.bakcell.nomre.mapstruct.ExceptionMapStruct;
import com.bakcell.nomre.model.response.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Javidan Alizada
 */
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final ExceptionMapStruct mapStruct;

    @ExceptionHandler(BadMSISDNException.class)
    public ResponseEntity<ExceptionResponse> badMSISDNException(BadMSISDNException ex, WebRequest request) {
        ExceptionResponse response = this.mapStruct.map(ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(BadPrefixException.class)
    public ResponseEntity<ExceptionResponse> badPrefixException(BadPrefixException ex, WebRequest request) {
        ExceptionResponse response = this.mapStruct.map(ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
        ExceptionResponse response = this.mapStruct.map(ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(CsvFileNotFoundException.class)
    public ResponseEntity<ExceptionResponse> csvFileNotFoundException(CsvFileNotFoundException ex, WebRequest request) {
        ExceptionResponse response = this.mapStruct.map(ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

}
