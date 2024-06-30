package com.personal.InventionMarket.exception;

import com.personal.InventionMarket.exception.userExceptions.ResourceDetailsInvalidException;
import com.personal.InventionMarket.exception.userExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceDetailsInvalidException.class)
    public ResponseEntity<String>  handleResourceDetailsInvalidException(ResourceDetailsInvalidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String>  handleResourceNotFoundException(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
