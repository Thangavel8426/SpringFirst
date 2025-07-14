package com.example.SpringbootwithDB.service;

import com.example.SpringbootwithDB.models.Todo;
import com.example.SpringbootwithDB.models.RegisterDetails;
import com.example.SpringbootwithDB.repository.RegisterDetailsRepository;
import com.example.SpringbootwithDB.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private RegisterDetailsRepository employeeRepository;

    public Todo createTodo(int empId, Todo todo) {
        RegisterDetails employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        todo.setEmployee(employee);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosByEmployeeId(int empId) {
        return todoRepository.findByEmployeeEmpId(empId);
    }

    public void deleteTodo(int id) {
        todoRepository.deleteById(id);
    }

    public Todo markComplete(int id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(true);
        return todoRepository.save(todo);
    }
}

