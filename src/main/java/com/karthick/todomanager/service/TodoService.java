package com.karthick.todomanager.service;

import com.karthick.todomanager.common.APIResponse;
import com.karthick.todomanager.dto.TodoDto;
import com.karthick.todomanager.entity.Todo;
import com.karthick.todomanager.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public APIResponse createTodo(Todo todo, int user_id) {
        todo.setUser_id(user_id);
        todoRepository.save(todo);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(todo);

        return apiResponse;
    }

    public APIResponse markTodoAsCompleted(int id, int user_id) {
        Todo todo = validate(id, user_id);
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(todo);

        return apiResponse;
    }

    public boolean deleteTodoById(int id, int user_id) {
        todoRepository.delete(validate(id, user_id));
        return true;
    }

    public Todo validate(int id, int user_id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null || todo.getUser_id() != user_id) {
            throw new NoSuchElementException("expecting data was not found");
        }
        return todo;
    }

    public List<TodoDto> getTodosByUserId(int user_id) {
        return todoRepository.findByUserId(user_id).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private TodoDto convertEntityToDto(Todo todo) {
        return (new ModelMapper()).map(todo, TodoDto.class);
    }
}
