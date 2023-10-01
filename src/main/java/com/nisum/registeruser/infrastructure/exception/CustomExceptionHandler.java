package com.nisum.registeruser.infrastructure.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException cause) {
            if (cause.getConstraintName().contains("email")) {
                ErrorResponse error = new ErrorResponse("El email ya está registrado.");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }
        ErrorResponse error = new ErrorResponse("Ocurrió un error al procesar la solicitud.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
