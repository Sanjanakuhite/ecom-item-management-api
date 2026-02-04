package com.ecommerce.exception;

import com.ecommerce.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Global Exception Handler
 * 
 * This class handles all exceptions thrown by controllers in the application.
 * It provides consistent error responses in the ApiResponse format.
 * 
 * Handled Exceptions:
 * - ItemNotFoundException: Returns HTTP 404
 * - MethodArgumentNotValidException: Returns HTTP 400 with validation errors
 * - Generic Exception: Returns HTTP 500
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ItemNotFoundException.
     * Returns HTTP 404 when requested item is not found.
     * 
     * @param ex The exception containing error details
     * @return ResponseEntity with error response and 404 status
     */
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleItemNotFoundException(
            ItemNotFoundException ex) {

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .errors(List.of("Resource not found"))
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles Validation Exceptions.
     * Returns HTTP 400 with detailed validation error messages.
     * 
     * This handles @Valid annotation failures on request bodies.
     * 
     * @param ex The exception containing field validation errors
     * @return ResponseEntity with validation errors and 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        // Extract all field validation errors
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("Validation failed. Please check the input fields.")
                .errors(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other unhandled exceptions.
     * Returns HTTP 500 for unexpected errors.
     * 
     * Note: In production, you would log the exception and return
     * a generic message without exposing internal details.
     * 
     * @param ex The unexpected exception
     * @return ResponseEntity with error response and 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .message("An unexpected error occurred")
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
