package com.example.springbootfirst.services;

import com.example.springbootfirst.models.Employee;
import com.example.springbootfirst.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HelloWorldService {
    @Autowired
    EmployeeRepository empRepo;

    public List<Employee> getMethod() {
        return empRepo.findAll();
    }

    public Employee getEmployeeById(int empID) {
        return empRepo.findById(empID).orElse(new Employee());
    }

    public String addEmployee(Employee employee) {
        empRepo.save(employee);
        return "Employee Added Successfully!!!";
    }

    public String updateEmployee(Employee employee) {
        empRepo.save(employee);
        return "Employee Updated Successfully!!!";
    }

    public String deleteEmployeeById(int empID) {
        empRepo.deleteById(empID);
        return "Employee Deleted Successfully!!!";
    }
}
