package com.example.demo.core.exception;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a structured error response returned to the client
 * when an exception occurs in the application.
 *
 * <p>This class is typically used by the {@code GlobalExceptionHandler}
 * to return consistent and informative error messages.</p>
 *
 * <p>Example JSON output:</p>
 * <pre>
 * {
 *   "message": "Validation failed",
 *   "errorCode": "VALIDATION_ERROR",
 *   "timestamp": "2025-05-16T13:00:00",
 *   "path": "/api/category",
 *   "details": [
 *     "name: must not be blank",
 *     "description: must not be blank"
 *   ]
 * }
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse {
    /**
     * A human-readable message describing the error.
     */
    private String message;

    /**
     * A unique error code used for identifying the error type.
     */
    private String errorCode;

    /**
     * The timestamp at which the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * The request path (URI) that caused the error.
     */
    private String path;

    /**
     * A list of specific error details (e.g., validation messages).
     */
    private List<String> details;
}
