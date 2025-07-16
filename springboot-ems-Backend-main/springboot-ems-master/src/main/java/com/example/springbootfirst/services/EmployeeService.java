package com.example.springbootfirst.services;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailsRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    RegisterDetailsRepositary details;

    public List<RegisterDetails> getMethod() {
        return details.findAll();
    }

    public RegisterDetails getEmployeeById(int empID) {
        return details.findById(empID).orElse(new RegisterDetails());
    }

    public String addEmployee(RegisterDetails employee) {
        details.save(employee);
        return "Employee Added Successfully!!!";
    }

    public String updateEmployee(RegisterDetails employee) {
        details.save(employee);
        return "Employee Updated Successfully!!!";
    }

    public String deleteEmployeeById(int empID) {
        details.deleteById(empID);
        return "Employee Deleted Successfully!!!";
    }
}
