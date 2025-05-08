package com.udacity.jdnd.course3.critter.exception;


import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
          .getAllErrors()
          .forEach((error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
          });
        throw new RuntimeException("Wrong parameters: " + errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations()
          .forEach(violation -> {
              String fieldName = violation.getPropertyPath()
                                          .toString();
              String errorMessage = violation.getMessage();
              errors.put(fieldName, errorMessage);
          });
        throw new RuntimeException("Wrong parameters: " + errors);
    }
}
