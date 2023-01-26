package com.karthick.todomanager.users;

import com.karthick.todomanager.common.APIResponse;
import com.karthick.todomanager.users.dto.LoginRequestDto;
import com.karthick.todomanager.users.dto.SignUpRequestDto;
import com.karthick.todomanager.util.JWTUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    private User convertDtoToEntity(SignUpRequestDto signUpRequestDto) {
        return (new ModelMapper()).map(signUpRequestDto, User.class);
    }

    public User getUserFromUserDto(SignUpRequestDto signUpRequestDto) {
        Optional<SignUpRequestDto> signUpRequestDto1 = Optional.ofNullable(signUpRequestDto);
        return signUpRequestDto1.stream().map(this::convertDtoToEntity).collect(Collectors.toList()).get(0);
    }

    public APIResponse signup(SignUpRequestDto signUpRequestDto) {
        User user = getUserFromUserDto(signUpRequestDto);
        userRepository.save(user);

        String token = jwtUtils.generateJWT(user);

        Map<String, Object> data = new HashMap<>();
        data.put("access_token", token);
        data.put("expires_in", 30000L);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(data);

        return apiResponse;
    }

    public APIResponse login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        APIResponse apiResponse = new APIResponse();
        if (user == null) {
            apiResponse.setData("user login failed");
            return apiResponse;
        }

        String token = jwtUtils.generateJWT(user);

        Map<String, Object> data = new HashMap<>();
        data.put("access_token", token);
        data.put("expires_in", 30000L);

        apiResponse.setData(data);

        return apiResponse;
    }
}
