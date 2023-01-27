package com.karthick.todomanager.controller;

import com.karthick.todomanager.common.APIResponse;
import com.karthick.todomanager.dto.RequestMetaDto;
import com.karthick.todomanager.entity.Todo;
import com.karthick.todomanager.dto.TodoDto;
import com.karthick.todomanager.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TodosController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private RequestMetaDto requestMetaDto;

    Logger logger = LoggerFactory.getLogger(TodosController.class);

    @GetMapping("/todos")
    public List<TodoDto> getTodosByUserId() {
        int user_id = requestMetaDto.getId();
        List<TodoDto> user = todoService.getTodosByUserId(user_id);
        if (user.isEmpty()) {
            logger.error("user_id:" + user_id + " is not found");
        }
        return user;
    }

    @PostMapping("/todo")
    public ResponseEntity<APIResponse> createTodo(@RequestBody Todo todo) {
        APIResponse apiResponse = todoService.createTodo(todo);
        logger.info("Creating a new todo");
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<APIResponse> updateTodoById(@PathVariable("id") int id) {
        APIResponse apiResponse = todoService.markTodoAsCompleted(id);
        logger.warn("Updating the todo information");
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @DeleteMapping("/todo/{id}")
    public boolean deleteTodoById(@PathVariable("id") int id) {
        logger.warn("Deleting the todo information");
        return todoService.deleteTodoById(id);
    }
}
