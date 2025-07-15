package com.example.SpringbootwithDB.service;

import com.example.SpringbootwithDB.models.RegisterDetails;
import com.example.SpringbootwithDB.models.UserDetailsDto;
import com.example.SpringbootwithDB.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired

    RegisterDetailsRepository registerDetailsRepository;

    public List<RegisterDetails> getMethod() {
        return registerDetailsRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empId) {
        return registerDetailsRepository.findById(empId).orElse(new RegisterDetails());
    }

//    public List<RegisterDetails> getEmployeeByJob() {
//        return registerDetailsRepository.findByRole();
//    }

    public String addEmployee(RegisterDetails employee) {
        registerDetailsRepository.save(employee);
        return "Employee Added Successfully";
    }

    public String updateRecords(int empId, UserDetailsDto updatedDetails) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("No Such User Present"));
        registerDetailsRepository.save(user);
        return "Employee Updated Successfully";
    }

    public String deleteEmployeeById(int empID) {
        registerDetailsRepository.deleteById(empID);
        return "Employee Deleted Successfully";
    }

    public List<RegisterDetails> getUsersByRole(String roleName) {
        return registerDetailsRepository.findByRoles_RoleName(roleName);
    }


    public RegisterDetails updateById(int id, RegisterDetails updatedEmployee) {
        return registerDetailsRepository.findById(id).map(exist -> {
            exist.setName(updatedEmployee.getName());
            exist.setEmail(updatedEmployee.getEmail());
            exist.setPassword(updatedEmployee.getPassword());
            exist.setUsername(updatedEmployee.getUsername());
            exist.setRoles(updatedEmployee.getRoles());
            return registerDetailsRepository.save(exist);
        }).orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }
}
