package com.karthick.todomanager.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto getUserById(int id) {
        return userRepository.findById(id).stream().map(this::convertEntityToDto).collect(Collectors.toList()).get(0);
    }

    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email).stream().map(this::convertEntityToDto).collect(Collectors.toList()).get(0);
    }

    private UserDto convertEntityToDto(User user) {
        return (new ModelMapper()).map(user, UserDto.class);
    }
}