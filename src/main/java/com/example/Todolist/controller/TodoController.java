package com.example.Todolist.controller;

import com.example.Todolist.model.Todomodel;
import com.example.Todolist.service.Todoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private Todoservice todoservice;

    @GetMapping("/todos")
    public List<Todomodel> getTodos() {
        return todoservice.getTodos();
    }

    @GetMapping("/todos/{taskno}")
    public Todomodel getTodoByTaskno(@PathVariable("taskno") int taskno) {
        return todoservice.getTodoByTaskno(taskno);
    }

    @PostMapping("/todos")
    public String addTodo(@RequestBody Todomodel todo) {
        todoservice.addTodo(todo);
        return "success";
    }

    @PutMapping("/todos/{taskno}")
    public String updateTodo(@PathVariable("taskno") int taskno, @RequestBody Todomodel todo) {
        boolean updated = todoservice.updateTodo(taskno, todo);
        return updated ? "updated successfully" : "task not found";
    }

    @DeleteMapping("/todos/{taskno}")
    public String deleteTodo(@PathVariable("taskno") int taskno) {
        boolean deleted = todoservice.deleteTodo(taskno);
        return deleted ? "deleted successfully" : "task not found";
    }
}
