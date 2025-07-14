package com.example.Todolist.service;

import com.example.Todolist.model.Todomodel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

@Service
public class Todoservice {

    private List<Todomodel> todos = new ArrayList<>(Arrays.asList(
            new Todomodel(1, "Learn Spring Boot", "Build a REST API"),
            new Todomodel(2, "Practice", "Make a simple Todo App")
    ));

    public List<Todomodel> getTodos() {
        return todos;
    }

    public Todomodel getTodoByTaskno(int taskno) {
        return todos.stream()
                .filter(todo -> todo.getTaskno() == taskno)
                .findFirst()
                .orElse(null);
    }

    public void addTodo(Todomodel todo) {
        todos.add(todo);
    }

    public boolean updateTodo(int taskno, Todomodel updatedTodo) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getTaskno() == taskno) {
                todos.set(i, updatedTodo);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTodo(int taskno) {
        Iterator<Todomodel> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todomodel todo = iterator.next();
            if (todo.getTaskno() == taskno) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
