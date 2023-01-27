package com.karthick.todomanager.service;

import com.karthick.todomanager.dto.UserDto;
import com.karthick.todomanager.entity.User;
import com.karthick.todomanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        return userRepository.findById(id).stream().map(this::convertEntityToDto).collect(Collectors.toList()).get(0);
    }

    private UserDto convertEntityToDto(User user) {
        return (new ModelMapper()).map(user, UserDto.class);
    }
}
