package com.arky.cotizaciones.config.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }




    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, String>> handleDuplicate(DuplicateResourceException ex){
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }



    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handlerErrorServer(Exception ex){
        Map<String, String> error = new HashMap<>();
        error.put("message", "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
