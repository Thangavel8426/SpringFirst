package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.UserDetails;
import com.example.springbootfirst.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userdetails")
public class UserDetailsController {

    @Autowired
    private UserDetailsRepository repository;

    @PostMapping
    public UserDetails createUser(@RequestBody UserDetails user) {
        return repository.save(user);
    }

    @GetMapping
    public List<UserDetails> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public UserDetails getUser(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public UserDetails updateUser(@PathVariable Long id, @RequestBody UserDetails updatedUser) {
        return repository.findById(id).map(user -> {
            user.setEmpName(updatedUser.getEmpName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setGender(updatedUser.getGender());
            user.setDob(updatedUser.getDob());
            return repository.save(user);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
