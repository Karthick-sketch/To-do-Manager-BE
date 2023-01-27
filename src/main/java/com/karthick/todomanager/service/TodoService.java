package com.karthick.todomanager.service;

import com.karthick.todomanager.common.APIResponse;
import com.karthick.todomanager.dto.TodoDto;
import com.karthick.todomanager.entity.Todo;
import com.karthick.todomanager.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public APIResponse createTodo(Todo todo) {
        todoRepository.save(todo);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(todo);

        return apiResponse;
    }

    public APIResponse markTodoAsCompleted(int id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setCompleted(!todo.isCompleted());
            todoRepository.save(todo);
        }

        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(todo);

        return apiResponse;
    }

    public boolean deleteTodoById(int id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            todoRepository.delete(todo);
            return true;
        }
        return false;
    }

    public List<TodoDto> getTodosByUserId(int user_id) {
        return todoRepository.findByUserId(user_id).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private TodoDto convertEntityToDto(Todo todo) {
        return (new ModelMapper()).map(todo, TodoDto.class);
    }
}
