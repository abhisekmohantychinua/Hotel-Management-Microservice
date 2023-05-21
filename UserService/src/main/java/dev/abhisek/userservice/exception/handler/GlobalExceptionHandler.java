package dev.abhisek.userservice.exception.handler;

import dev.abhisek.userservice.exception.ResourceNotFoundException;
import dev.abhisek.userservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse
                        .builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }
}
