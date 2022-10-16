package com.karthick.todomanager.service;

import com.karthick.todomanager.datarepository.TodoRepository;
import com.karthick.todomanager.dto.TodoDto;
import com.karthick.todomanager.model.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepo;

    public List<TodoDto> getTodosByUserId(int user_id) {
        return todoRepo.findByUserId(user_id).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private TodoDto convertEntityToDto(Todo todo) {
        return (new ModelMapper()).map(todo, TodoDto.class);
    }
}
