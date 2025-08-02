package com.lablogbook.backend.controller;

import com.lablogbook.backend.dto.ExperimentDTO;
import com.lablogbook.backend.entity.ExperimentStatus;
import com.lablogbook.backend.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiments")
@CrossOrigin(origins = "http://localhost:3000")
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService;

    @GetMapping
    public ResponseEntity<List<ExperimentDTO>> getAllExperiments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<ExperimentDTO> experiments = experimentService.getExperimentsByUsername(username);
        return ResponseEntity.ok(experiments);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ExperimentDTO>> getExperimentsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ExperimentDTO> experiments = experimentService.getAllExperiments(PageRequest.of(page, size));
        return ResponseEntity.ok(experiments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperimentDTO> getExperimentById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return experimentService.getExperimentById(id, username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExperimentDTO>> getExperimentsByUser(@PathVariable Long userId) {
        List<ExperimentDTO> experiments = experimentService.getExperimentsByUser(userId);
        return ResponseEntity.ok(experiments);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ExperimentDTO>> getExperimentsByStatus(@PathVariable ExperimentStatus status) {
        List<ExperimentDTO> experiments = experimentService.getExperimentsByStatus(status);
        return ResponseEntity.ok(experiments);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ExperimentDTO>> searchExperiments(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ExperimentDTO> experiments = experimentService.searchExperiments(keyword, PageRequest.of(page, size));
        return ResponseEntity.ok(experiments);
    }

    @PostMapping
    public ResponseEntity<ExperimentDTO> createExperiment(@RequestBody ExperimentDTO experimentDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            ExperimentDTO createdExperiment = experimentService.createExperiment(experimentDTO, username);
            return ResponseEntity.ok(createdExperiment);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperimentDTO> updateExperiment(@PathVariable Long id,
                                                         @RequestBody ExperimentDTO experimentDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            ExperimentDTO updatedExperiment = experimentService.updateExperiment(id, experimentDTO, username);
            return ResponseEntity.ok(updatedExperiment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperiment(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            experimentService.deleteExperiment(id, username);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ExperimentDTO> updateExperimentStatus(@PathVariable Long id,
                                                               @RequestParam ExperimentStatus status) {
        try {
            ExperimentDTO updatedExperiment = experimentService.updateExperimentStatus(id, status);
            return ResponseEntity.ok(updatedExperiment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
} 