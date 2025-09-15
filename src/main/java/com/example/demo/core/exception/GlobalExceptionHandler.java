package com.example.demo.core.exception;

import com.example.demo.core.constants.Constants;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GlobalExceptionHandler handles application-wide exceptions and provides standardized error responses.
 * This class uses Spring's {@link RestControllerAdvice} to catch and process exceptions thrown
 * from any REST controller method in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException and returns a 404 NOT FOUND response.
     *
     * @param ex      the ResourceNotFoundException thrown
     * @param request the current web request
     * @return ResponseEntity containing the structured error response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        // Create an ErrorResponse object with the error details
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(Constants.RESOURCE_NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .path(getPath(request))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    /**
     * Handles validation failures caused by invalid method arguments annotated with {@code @Valid}.
     * Returns a 422 UNPROCESSABLE ENTITY response with field-level validation error details.
     *
     * @param ex      the MethodArgumentNotValidException thrown
     * @param request the current web request
     * @return ResponseEntity containing the structured error response with validation messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        // Extract field errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        // Create an ErrorResponse object with the errors
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Validation failed")
                .errorCode(Constants.VALIDATION_ERROR)
                .timestamp(LocalDateTime.now())
                .path(getPath(request))
                .details(errors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles validation errors triggered by constraints on query parameters or path variables
     * annotated with {@code @Validated}. Returns a 400 BAD REQUEST response.
     *
     * @param ex      the ConstraintViolationException thrown
     * @param request the current web request
     * @return ResponseEntity containing the structured error response with violation messages
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

        List<String> violations = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        // Create an ErrorResponse object with the violations
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Validation error")
                .errorCode(Constants.CONSTRAINT_VIOLATION)
                .timestamp(LocalDateTime.now())
                .path(getPath(request))
                .details(violations)
                .build();


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * ✅ Handles DataIntegrityViolationException (DB constraint failures like unique key violations)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Data integrity violation: " + extractRootCause(ex))
                .errorCode(Constants.DATA_INTEGRITY_VIOLATION)
                .timestamp(LocalDateTime.now())
                .path(getPath(request))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * ✅ Handles RuntimeException (generic fallback for unexpected errors)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("An unexpected error occurred. Please contact support.")
                .errorCode(Constants.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .path(getPath(request))
                .details(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Utility method to extract root cause message from exception
     */
    private String extractRootCause(Throwable ex) {
        Throwable root = ex;
        while (root.getCause() != null) {
            root = root.getCause();
        }
        return root.getMessage();
    }

    /**
     * Utility method to extract clean request URI
     */
    private String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

}