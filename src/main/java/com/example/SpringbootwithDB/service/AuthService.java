package com.example.SpringbootwithDB.service;

import com.example.SpringbootwithDB.Jwt.JwtTokenProvider;
import com.example.SpringbootwithDB.models.RegisterDetails;
import com.example.SpringbootwithDB.models.Roles;
import com.example.SpringbootwithDB.models.UserDetailsDto;
import com.example.SpringbootwithDB.repository.RegisterDetailsRepository;
import com.example.SpringbootwithDB.repository.RegisterRepository;
import com.example.SpringbootwithDB.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String addNewEmployee(UserDetailsDto register) {
        if (register.getPassword() == null || register.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setUsername(register.getUsername());
        registerDetails.setName(register.getName());
        registerDetails.setPassword(encoder.encode(register.getPassword()));

        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleName()) {
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role Not Found: " + roleName));
            roles.add(role);
        }

        registerDetails.setRoles(roles);
        registerDetailsRepository.save(registerDetails);
        return "Employee Added Successfully";
    }

    public String authenticate(RegisterDetails login)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (login.getUsername(),login.getPassword()));

        return jwtTokenProvider.generateToken(authentication);
    }

    public Optional<RegisterDetails> getUserUsername(String username)
    {
        return registerRepository.findByUsername(username);
    }


}


