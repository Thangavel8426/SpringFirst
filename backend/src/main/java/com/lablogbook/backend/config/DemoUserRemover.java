package com.lablogbook.backend.config;

import com.lablogbook.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoUserRemover implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        String demoUsername = "demo";
        
        if (userService.existsByUsername(demoUsername)) {
            try {
                userService.deleteUserByUsername(demoUsername);
                System.out.println("✅ Demo user 'demo' has been removed successfully.");
            } catch (Exception e) {
                System.err.println("❌ Failed to remove demo user 'demo': " + e.getMessage());
            }
        } else {
            System.out.println("ℹ️ Demo user 'demo' does not exist.");
        }
    }
} 