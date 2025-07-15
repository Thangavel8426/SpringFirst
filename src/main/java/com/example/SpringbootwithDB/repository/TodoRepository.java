package com.example.SpringbootwithDB.repository;

import com.example.SpringbootwithDB.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByEmployeeEmpId(int empId);
}
