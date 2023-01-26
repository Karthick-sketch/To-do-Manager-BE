package com.karthick.todomanager.todos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@EnableCaching
public class TodosController {
    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private TodoService todoService;

    Logger logger = LoggerFactory.getLogger(TodosController.class);

    @GetMapping("/todos/{user_id}")
    @Cacheable(key = "#user_id", value = "Todo")
    public List<TodoDto> getTodosByUserId(@PathVariable("user_id") int user_id) {
        List<TodoDto> user = todoService.getTodosByUserId(user_id);
        if(user.isEmpty()) {
            logger.error("user_id:" + user_id + " is not found");
        }
        return user;
    }

    @PostMapping("/todo")
    public Todo createTodo(@RequestBody Todo todo) {
        todoRepo.save(todo);
        logger.info("Creating a new todo");
        return todo;
    }

    @PatchMapping("/todo/{id}")
    public boolean markTodoAsCompleted(@PathVariable("id") int id) {
        Todo todo = todoRepo.getById(id);
        todo.setCompleted(!todo.isCompleted());
        todoRepo.save(todo);
        logger.warn("Updating the todo information");
        return todo.isCompleted();
    }

    @DeleteMapping("/todo/{id}")
    public boolean deleteTodoById(@PathVariable("id") int id) {
        Todo todo = todoRepo.findById(id).orElse(null);
        if (todo != null) {
            logger.warn("Deleting the todo information");
            todoRepo.delete(todo);
            return true;
        }
        return false;
    }
}
