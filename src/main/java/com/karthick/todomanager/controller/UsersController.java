package com.karthick.todomanager.controller;

import com.karthick.todomanager.datarepo.UserRepo;
import com.karthick.todomanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UsersController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") int id) {
        return userRepo.findById(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        userRepo.save(user);
        return user;
    }
}
