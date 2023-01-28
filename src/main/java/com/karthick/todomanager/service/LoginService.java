package com.karthick.todomanager.service;

import com.karthick.todomanager.common.APIResponse;
import com.karthick.todomanager.common.BadRequestException;
import com.karthick.todomanager.dto.ErrorDto;
import com.karthick.todomanager.dto.LoginRequestDto;
import com.karthick.todomanager.dto.SignUpRequestDto;
import com.karthick.todomanager.entity.User;
import com.karthick.todomanager.repository.UserRepository;
import com.karthick.todomanager.util.JWTUtils;
import com.karthick.todomanager.validate.LoginValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private LoginValidation loginValidation;

    private final long expiryDuration = 120000L;

    private User convertDtoToEntity(SignUpRequestDto signUpRequestDto) {
        return (new ModelMapper()).map(signUpRequestDto, User.class);
    }

    public User getUserFromUserDto(SignUpRequestDto signUpRequestDto) {
        Optional<SignUpRequestDto> signUpRequestDto1 = Optional.ofNullable(signUpRequestDto);
        return signUpRequestDto1.stream().map(this::convertDtoToEntity).collect(Collectors.toList()).get(0);
    }

    public APIResponse signup(SignUpRequestDto signUpRequestDto) {
        List<ErrorDto> errors = loginValidation.validateSignup(signUpRequestDto);
        if (errors.size() > 0) {
            throw new BadRequestException("bad request", errors);
        }

        User user = getUserFromUserDto(signUpRequestDto);
        userRepository.save(user);

        String token = jwtUtils.generateJWT(user);

        Map<String, Object> data = new HashMap<>();
        data.put("access_token", token);
        data.put("expires_in", expiryDuration);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(data);

        return apiResponse;
    }

    public APIResponse login(LoginRequestDto loginRequestDto) {
        List<ErrorDto> errors = loginValidation.validateLogin(loginRequestDto);
        if (errors.size() > 0) {
            throw new BadRequestException("bad request", errors);
        }

        User user = userRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        APIResponse apiResponse = new APIResponse();
        if (user == null) {
            throw new NoSuchElementException("email or password is invalid");
        }

        String token = jwtUtils.generateJWT(user);

        Map<String, Object> data = new HashMap<>();
        data.put("access_token", token);
        data.put("expires_in", expiryDuration);

        apiResponse.setData(data);

        return apiResponse;
    }
}
