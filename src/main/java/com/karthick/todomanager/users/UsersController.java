package com.karthick.todomanager.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/signin/{email}")
    public UserDto getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }
}
