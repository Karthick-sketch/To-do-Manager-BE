package com.karthick.todomanager.validate;

import com.karthick.todomanager.dto.ErrorDto;
import com.karthick.todomanager.dto.LoginRequestDto;
import com.karthick.todomanager.dto.SignUpRequestDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidation {
    public List<ErrorDto> validateSignup(SignUpRequestDto signUpRequestDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (signUpRequestDto.getName() == null) {
            errors.add(createNewError("username"));
        }
        if (signUpRequestDto.getEmail() == null) {
            errors.add(createNewError("email"));
        }
        if (signUpRequestDto.getPassword() == null) {
            errors.add(createNewError("password"));
        }
        return errors;
    }

    public List<ErrorDto> validateLogin(LoginRequestDto loginRequestDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (loginRequestDto.getEmail() == null) {
            errors.add(createNewError("email"));
        }
        if (loginRequestDto.getPassword() == null) {
            errors.add(createNewError("password"));
        }
        return errors;
    }

    private ErrorDto createNewError(String target) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setTarget(target);
        errorDto.setMessage("missing field :" + target);
        return errorDto;
    }
}
