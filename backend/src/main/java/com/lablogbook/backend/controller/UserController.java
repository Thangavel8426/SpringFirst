package com.lablogbook.backend.controller;

import com.lablogbook.backend.dto.UserDTO;
import com.lablogbook.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(username, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUserByUsername(username);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
