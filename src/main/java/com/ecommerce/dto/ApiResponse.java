package com.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Generic API Response Wrapper
 * 
 * This class provides a consistent response structure for all API endpoints.
 * It wraps the actual data with metadata about the request status.
 * 
 * Response Structure:
 * {
 * "success": true/false,
 * "message": "description",
 * "data": { ... },
 * "errors": [...],
 * "timestamp": "2024-01-01T12:00:00"
 * }
 * 
 * @param <T> The type of data being returned
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON
public class ApiResponse<T> {

    /**
     * Indicates whether the request was successful.
     */
    private boolean success;

    /**
     * Human-readable message describing the result.
     */
    private String message;

    /**
     * The actual data payload.
     * This can be a single object, list, or any serializable type.
     */
    private T data;

    /**
     * List of validation or error messages.
     * Only present when there are errors.
     */
    private List<String> errors;

    /**
     * Timestamp of when the response was generated.
     */
    private LocalDateTime timestamp;

    // Default constructor
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor with all fields
    public ApiResponse(boolean success, String message, T data, List<String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    // Static builder-style factory methods
    public static <T> ApiResponseBuilder<T> builder() {
        return new ApiResponseBuilder<>();
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Builder class for ApiResponse
     */
    public static class ApiResponseBuilder<T> {
        private boolean success;
        private String message;
        private T data;
        private List<String> errors;

        public ApiResponseBuilder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder<T> errors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(success, message, data, errors);
        }
    }
}
