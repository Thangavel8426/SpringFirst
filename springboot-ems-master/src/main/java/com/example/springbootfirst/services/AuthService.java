package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepositary;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private RegisterDetailsRepositary registerDetailsRepositary;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
       // registerDetails.setEmpID(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUsername(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleNames()) {
            Roles role = rolesRepository.findByRoleName(roleName).orElseGet(() -> {
                Roles newRole = new Roles();
                newRole.setRoleName(roleName);
                return rolesRepository.save(newRole);
            });
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        registerDetailsRepositary.save(registerDetails);

        return "Employee Added Successfully";
    }

    public String authenticate(RegisterDetails login) {
        RegisterDetails user = registerDetailsRepositary.findByEmail(login.getEmail());

        if (user == null) {
            return "User not found";
        }
        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            return "Wrong Password";
        }
        return "Login successful username: " + user.getName();
    }
    // Inside AuthService.java

    public List<RegisterDetails> getMethod() {
        return registerDetailsRepositary.findAll();
    }

    public RegisterDetails getRegisterById(int empID) {
        return registerDetailsRepositary.findById(empID).orElse(null);
    }

    public String saveRegister(RegisterDetails registerDetails) {
        registerDetails.setPassword(passwordEncoder.encode(registerDetails.getPassword()));
        registerDetailsRepositary.save(registerDetails);
        return "User Registered Successfully!";
    }

    public String updateRegister(RegisterDetails registerDetails) {
        registerDetails.setPassword(passwordEncoder.encode(registerDetails.getPassword()));
        registerDetailsRepositary.save(registerDetails);
        return "User Updated Successfully!";
    }

    public String updateRegisterById(int id, RegisterDetails updatedDetails) {
        RegisterDetails existingUser = registerDetailsRepositary.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        existingUser.setName(updatedDetails.getName());
        existingUser.setEmail(updatedDetails.getEmail());
        existingUser.setUsername(updatedDetails.getUsername());
        existingUser.setPassword(passwordEncoder.encode(updatedDetails.getPassword()));
        existingUser.setRoles(updatedDetails.getRoles());

        registerDetailsRepositary.save(existingUser);
        return "User with ID " + id + " updated successfully!";
    }

    public String deleteRegisterById(int empID) {
        registerDetailsRepositary.deleteById(empID);
        return "User Deleted Successfully!";
    }

    public List<RegisterDetails> getUsersByRole(String roleName) {
        return registerDetailsRepositary.findByRolesRoleName(roleName);
    }



}
