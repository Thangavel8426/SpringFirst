package com.example.SpringbootwithDB.controllers;

import com.example.SpringbootwithDB.models.Todo;
import com.example.SpringbootwithDB.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/employee/{empId}")
    public Todo addTodo(@PathVariable int empId, @RequestBody Todo todo) {
        return todoService.createTodo(empId, todo);
    }

    @GetMapping("/employee/{empId}")
    public List<Todo> getTodos(@PathVariable int empId) {
        return todoService.getTodosByEmployeeId(empId);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }

    @PutMapping("/complete/{id}")
    public Todo markAsComplete(@PathVariable int id) {
        return todoService.markComplete(id);
    }
}
