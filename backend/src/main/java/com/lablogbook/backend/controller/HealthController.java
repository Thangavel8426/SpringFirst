package com.lablogbook.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", System.currentTimeMillis());
        
        // Test database connection
        try (Connection connection = dataSource.getConnection()) {
            response.put("database", "UP");
            response.put("databaseUrl", connection.getMetaData().getURL());
        } catch (SQLException e) {
            response.put("database", "DOWN");
            response.put("databaseError", e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
} 