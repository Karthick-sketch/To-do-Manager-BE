package com.karthick.todomanager.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TodosController {
    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos/{user_id}")
    public List<TodoDto> getTodosByUserId(@PathVariable("user_id") int user_id) {
        return todoService.getTodosByUserId(user_id);
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
    public boolean deleteTodoById(@PathVariable("id") int id) {
        Todo todo = todoRepo.findById(id).orElse(null);
        if (todo != null) {
            todoRepo.delete(todo);
            return true;
        }
        return false;
    }
}
