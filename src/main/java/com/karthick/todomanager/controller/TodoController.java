package com.karthick.todomanager.controller;

import com.karthick.todomanager.datarepo.TodoRepo;
import com.karthick.todomanager.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    TodoRepo todoRepo;

    @PostMapping("/todo")
    public Todo createTodo(@RequestBody Todo todo) {
        todoRepo.save(todo);
        return todo;
    }

    @GetMapping("/todo/{id}")
    public Optional<Todo> getTodoById(@PathVariable("id") int id) {
        return todoRepo.findById(id);
    }

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoRepo.findAll();
    }

    @PatchMapping("/todo/{id}")
    public boolean markAsCompleted(@PathVariable("id") int id) {
        Todo todo = todoRepo.getById(id);
        todo.setCompleted(!todo.isCompleted());
        todoRepo.save(todo);
        System.out.println(todo.toString());
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
