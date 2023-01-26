package com.karthick.todomanager.users;

import com.karthick.todomanager.common.APIResponse;
import com.karthick.todomanager.users.dto.LoginRequestDto;
import com.karthick.todomanager.users.dto.SignUpRequestDto;
import com.karthick.todomanager.users.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
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
