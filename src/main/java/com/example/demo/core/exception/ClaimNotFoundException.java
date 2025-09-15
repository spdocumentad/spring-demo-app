package com.example.demo.core.exception;

/**
 * Exception thrown when a specific claim is not found in the JWT token.
 * <p>
 * This is typically used in security-related contexts where a required
 * claim is expected to be present in the token but is missing.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * String userId = tokenUtil.getClaim("user_id");
 * }</pre>
 */
public class ClaimNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code ClaimNotFoundException} with a default message "Claim not found".
     */
    public ClaimNotFoundException() {
        super("Claim not found");
    }

    /**
     * Constructs a new {@code ClaimNotFoundException} with a message indicating which claim is missing.
     *
     * @param claimName the name of the missing claim
     */
    public ClaimNotFoundException(String claimName) {
        super("Claim not found: " + claimName);
    }

    /**
     * Constructs a new {@code ClaimNotFoundException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public ClaimNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
