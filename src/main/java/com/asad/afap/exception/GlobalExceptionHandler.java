package com.asad.afap.exception;


import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("status", 401, "message", "Invalid Email or Password"));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status",  400 , "message", ex.getMessage()));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, Object>> handleSQLException(SQLException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status", 500 , "message", ex.getMessage()));
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Map<String, Object>> handleUnexpectedTypeException(UnexpectedTypeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status", 400 , "message", ex.getMessage()));
    }
}
