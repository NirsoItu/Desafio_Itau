package com.itau.desafio.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={ResponseStatusException.class})
    public ResponseEntity<Object> handleUnprocessableEntityException(Exception e, WebRequest request){

        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.getLocalizedMessage();

        Integer errorStatus = HttpStatus.UNPROCESSABLE_ENTITY.value();

        ErrorMessage errorMessage = new ErrorMessage(errorStatus, errorDescription);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value={NullPointerException.class})
    public ResponseEntity<Object> handleInternalException(Exception e, WebRequest request){

        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = HttpStatus.INTERNAL_SERVER_ERROR.toString();

        Integer errorStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ErrorMessage errorMessage = new ErrorMessage(errorStatus, errorDescription);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
