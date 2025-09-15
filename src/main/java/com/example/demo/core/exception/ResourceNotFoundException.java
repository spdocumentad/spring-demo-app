package com.example.demo.core.exception;

/**
 * Exception thrown when a requested resource is not found in the system.
 * <p>
 * This exception can be used in service or controller layers to signal that an resource
 * with a specified ID or criteria does not exist in the database or storage.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Resource resource = resourceRepository.findById(id)
 *     .orElseThrow(() -> new ResourceNotFoundException("Resource with ID " + id + " not found"));
 * }</pre>
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new {@code ResourceNotFoundException} with a default message "Resource not found".
     */
    public ResourceNotFoundException() {
        super("Resource not found");
    }

    /**
     * Constructs a new {@code ResourceNotFoundException} with a custom message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ResourceNotFoundException} with a custom message and cause.
     *
     * @param message the detail message
     * @param cause   the cause (a throwable that caused this exception)
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}