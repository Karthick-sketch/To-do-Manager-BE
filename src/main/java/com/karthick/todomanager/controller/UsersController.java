package com.karthick.todomanager.controller;

import com.karthick.todomanager.common.APIResponse;
import com.karthick.todomanager.dto.LoginRequestDto;
import com.karthick.todomanager.dto.RequestMetaDto;
import com.karthick.todomanager.dto.SignUpRequestDto;
import com.karthick.todomanager.service.LoginService;
import com.karthick.todomanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RequestMetaDto requestMetaDto;

    @GetMapping("/users")
    public ResponseEntity<APIResponse> getUsers() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(userService.getAllUsers());
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @GetMapping("/user/")
    public ResponseEntity<APIResponse> getUserById() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(userService.getUserById(requestMetaDto.getId()));
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        APIResponse apiResponse = loginService.signup(signUpRequestDto);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

    @GetMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        APIResponse apiResponse = loginService.login(loginRequestDto);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
