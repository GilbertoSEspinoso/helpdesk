package com.gbproductions.helpdesk.resources.exceptions;

import com.gbproductions.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    //MANIPULADOR DE EXCEPTION PERSONALIZADA -> VALIDACAO DE ID
    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {

        StandardError error = new StandardError(
                                                Instant.now(),
                                                HttpStatus.NOT_FOUND.value(),
                                                "Object not found.",
                                                ex.getMessage(),
                                                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //MANIPULADOR DE EXCEPTION PERSONALIZADA -> VALIDACAO DE CPF e EMAIL
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {

        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Breach.",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
