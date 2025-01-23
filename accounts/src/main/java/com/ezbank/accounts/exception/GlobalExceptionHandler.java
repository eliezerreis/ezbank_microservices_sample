package com.ezbank.accounts.exception;

import com.ezbank.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler (CustomerExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handle(CustomerExistsException e, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            request.getDescription(false),
            HttpStatus.BAD_REQUEST,
            e.getMessage(),
            LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(ResourceNotFoundException e, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            request.getDescription(false),
            HttpStatus.NOT_FOUND,
            e.getMessage(),
            LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorResponseDTO> handle(Exception e, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            request.getDescription(false),
            HttpStatus.INTERNAL_SERVER_ERROR,
            e.getMessage(),
            LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });


        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
