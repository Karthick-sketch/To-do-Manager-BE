package com.karthick.todomanager.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<APIResponse> handleAccessDeniedException(AccessDeniedException ade) {
        APIResponse apiResponse = new APIResponse();

        apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        apiResponse.setError(ade.getMessage());

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
