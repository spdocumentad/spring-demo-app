package com.example.demo.core.constants;

import org.springframework.stereotype.Component;

/**
 * A utility class that holds constant values used across the application.
 *
 * <p>These constants are typically used for:
 * <ul>
 *   <li>Error code identifiers for structured error responses</li>
 *   <li>Media type definitions</li>
 *   <li>Threshold configurations</li>
 * </ul>
 * </p>
 */
@Component
public class Constants {

    /**
     * Error code indicating that a requested asset was not found.
     */
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";

    /**
     * Error code used for general validation errors.
     */
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";

    /**
     * Error code for violations detected by constraint annotations (e.g., {@code @NotNull}, {@code @Size}).
     */
    public static final String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";

    /**
     * Error code indicating a data integrity violation, such as unique constraint breaches.
     */
    public static final String DATA_INTEGRITY_VIOLATION = "DATA_INTEGRITY_VIOLATION";

    /**
     * Error code for unexpected server errors.
     */
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
}
