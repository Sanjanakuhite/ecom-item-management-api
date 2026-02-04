package com.ecommerce.exception;

/**
 * Item Not Found Exception
 * 
 * Custom exception thrown when an item with the specified ID
 * cannot be found in the data store.
 * 
 * This exception is caught by the GlobalExceptionHandler and
 * converted to an appropriate HTTP 404 response.
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
public class ItemNotFoundException extends RuntimeException {

    /**
     * Constructs a new ItemNotFoundException with the specified message.
     * 
     * @param message The detail message explaining which item was not found
     */
    public ItemNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ItemNotFoundException with message and cause.
     * 
     * @param message The detail message
     * @param cause   The underlying cause of the exception
     */
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
