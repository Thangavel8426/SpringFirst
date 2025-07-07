package com.example.Springbootfirst.services;

import com.example.Springbootfirst.models.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HelloWorldService {

    List<Employee> emp = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "Prasanth", "Trainer"),
                    new Employee(2, "Yuvaraj", "Faculty")
            )
    );

    public String GetMethod() {
        return "This is Get Method";
    }

    public Employee getEmployeeById(int empid) {
        for (int i = 0; i < emp.size(); i++) {
            Employee e = emp.get(i);
            if (e.getEmpid() == empid) {
                return e;
            }
        }
        return new Employee();
    }
    public String postMethod(Employee employee) {
        emp.add(employee);
        return "Employee is added successfully";
    }

    public String PutMethod() {
        return "This is Put Method";
    }

    public String Deletemethod() {
        return "This is Delete Method";
    }


    public List<Employee> getAllEmployees() {
        return emp;
    }

    public void addEmployee(Employee employee) {
        emp.add(employee);
    }

    public String deleteEmployeeById(int empid) {
        for (int i = 0; i < emp.size(); i++) {
            if (emp.get(i).getEmpid() == empid) {
                emp.remove(i);
                return "Employee with ID " + empid + " deleted successfully.";
            }
        }
        return "Employee with ID " + empid + " not found.";
    }

    public String updateRecords(int empid, Employee employee) {
        for (int i = 0; i < emp.size(); i++) {
            Employee current = emp.get(i);
            if (current.getEmpid() == empid) {
                current.setName(employee.getName());
                current.setJob(employee.getJob());
                return "Employee with ID " + empid + " updated successfully.";
            }
        }
        return "Employee with ID " + empid + " not found.";
    }


}
