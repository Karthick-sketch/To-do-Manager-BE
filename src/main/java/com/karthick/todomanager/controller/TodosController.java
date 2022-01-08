package com.karthick.todomanager.controller;

import com.karthick.todomanager.datarepo.TodoRepo;
import com.karthick.todomanager.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TodosController {
    @Autowired
    TodoRepo todoRepo;

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoRepo.findAll();
    }

    @GetMapping("/todos/{user_id}")
    public List<Todo> getTodos(@PathVariable("user_id") int user_id) {
        return todoRepo.findByUserId(user_id);
    }

    @PostMapping("/todo")
    public Todo createTodo(@RequestBody Todo todo) {
        todoRepo.save(todo);
        return todo;
    }

    @PatchMapping("/todo/{id}")
    public boolean markTodoAsCompleted(@PathVariable("id") int id) {
        Todo todo = todoRepo.getById(id);
        todo.setCompleted(!todo.isCompleted());
        todoRepo.save(todo);
        return todo.isCompleted();
    }

    @DeleteMapping("/todo/{id}")
    public String deleteTodo(@PathVariable("id") int id) {
        Todo todo = todoRepo.findById(id).orElse(null);
        if (todo != null) {
            todoRepo.delete(todo);
            return "deleted";
        }
        return "record is not exists";
    }
}
