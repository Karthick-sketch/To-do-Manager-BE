package com.karthick.todomanager.common;

import com.karthick.todomanager.dto.ErrorDto;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<ErrorDto> errors;

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public BadRequestException(String message, List<ErrorDto> errors) {
        super(message);
        this.errors = errors;
    }
}
