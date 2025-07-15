package com.example.SpringbootwithDB.controllers;

import com.example.SpringbootwithDB.models.RegisterDetails;
import com.example.SpringbootwithDB.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String route() {
        return "Welcome to Springboot Security";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee")
    public List<RegisterDetails> getAllEmployees() {
        return employeeService.getMethod();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/employee/{empId}")
    public RegisterDetails getEmployeeById(@PathVariable int empId) {
        System.out.println();
        return employeeService.getEmployeeById(empId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employee/role/{role}")
    public List<RegisterDetails> getUsersByRole(@PathVariable String role) {
        return employeeService.getUsersByRole(role);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    public String postmethod(@RequestBody RegisterDetails employee) {
        return employeeService.addEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employee/{empid}")
    public String PutMethod(@PathVariable int empid, @RequestBody RegisterDetails updatedDetails) {
        return employeeService.updateRecords(empid,updatedDetails);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employee/{empid}")
    public String deleteMethod(@PathVariable int empid)
    {
        return employeeService.deleteEmployeeById(empid);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RegisterDetails> updateEmployeeById(
            @PathVariable int id,
            @RequestBody RegisterDetails updatedDetails) {
        RegisterDetails updated = employeeService.updateById(id, updatedDetails);
        return ResponseEntity.ok(updated);
    }



}
