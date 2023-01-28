package com.karthick.todomanager.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<APIResponse> handleAccessDeniedException(AccessDeniedException ade) {
        APIResponse apiResponse = new APIResponse();

        apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        apiResponse.setError(ade.getMessage());

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @ExceptionHandler
    public ResponseEntity<APIResponse> handleNoSuchElementException(NoSuchElementException nsee) {
        APIResponse apiResponse = new APIResponse();

        apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
        apiResponse.setError(nsee.getMessage());

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @ExceptionHandler
    public ResponseEntity<APIResponse> handleBadRequestException(BadRequestException bre) {
        APIResponse apiResponse = new APIResponse();

        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setError(bre.getErrors());

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
