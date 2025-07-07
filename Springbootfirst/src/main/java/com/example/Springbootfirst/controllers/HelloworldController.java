package com.example.Springbootfirst.controllers;

import com.example.Springbootfirst.models.Employee;
import com.example.Springbootfirst.services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class HelloworldController {

    @Autowired
    HelloWorldService hws;

    @GetMapping
    public String hello() {
        return hws.GetMethod();
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return hws.getAllEmployees();
    }

    @GetMapping("/{empid}")
    public Employee getEmployeeById(@PathVariable int empid) {
        return hws.getEmployeeById(empid);
    }

    @PostMapping
    public String postMethod(@RequestBody Employee employee) {
        return hws.postMethod(employee);
    }

    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee) {
        hws.addEmployee(employee);
        return "Employee added successfully";
    }

    @PutMapping("/{empid}")
    public String putMethod(@PathVariable int empid, @RequestBody Employee employee)
    {
        return hws.updateRecords(empid, employee);
    }


    @DeleteMapping
    public String deleteMethod() {
        return hws.Deletemethod();
    }

    @DeleteMapping("/{empid}")
    public String deleteEmployee(@PathVariable int empid) {
        return hws.deleteEmployeeById(empid);
    }
}
