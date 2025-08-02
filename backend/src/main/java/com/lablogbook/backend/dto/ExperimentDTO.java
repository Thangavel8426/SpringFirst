package com.lablogbook.backend.dto;

import com.lablogbook.backend.entity.ExperimentStatus;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExperimentDTO {
    private Long id;
    
    @NotBlank
    private String title;
    
    private String objective;
    private String hypothesis;
    private String materials;
    private String procedureSteps;
    private String observations;
    private String results;
    private String conclusion;
    private ExperimentStatus status;
    private Long userId;
    private String userFullName;
    private LocalDateTime experimentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ExperimentImageDTO> images;
    
    // Constructors
    public ExperimentDTO() {
    }
    
    public ExperimentDTO(Long id, String title, String objective, ExperimentStatus status, Long userId) {
        this.id = id;
        this.title = title;
        this.objective = objective;
        this.status = status;
        this.userId = userId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getObjective() {
        return objective;
    }
    
    public void setObjective(String objective) {
        this.objective = objective;
    }
    
    public String getHypothesis() {
        return hypothesis;
    }
    
    public void setHypothesis(String hypothesis) {
        this.hypothesis = hypothesis;
    }
    
    public String getMaterials() {
        return materials;
    }
    
    public void setMaterials(String materials) {
        this.materials = materials;
    }
    
    public String getProcedureSteps() {
        return procedureSteps;
    }
    
    public void setProcedureSteps(String procedureSteps) {
        this.procedureSteps = procedureSteps;
    }
    
    public String getObservations() {
        return observations;
    }
    
    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public String getResults() {
        return results;
    }
    
    public void setResults(String results) {
        this.results = results;
    }
    
    public String getConclusion() {
        return conclusion;
    }
    
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    
    public ExperimentStatus getStatus() {
        return status;
    }
    
    public void setStatus(ExperimentStatus status) {
        this.status = status;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserFullName() {
        return userFullName;
    }
    
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    
    public LocalDateTime getExperimentDate() {
        return experimentDate;
    }
    
    public void setExperimentDate(LocalDateTime experimentDate) {
        this.experimentDate = experimentDate;
    }
    
    // Overloaded setter to handle date strings from frontend
    public void setExperimentDate(String experimentDate) {
        if (experimentDate != null && !experimentDate.trim().isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(experimentDate);
                this.experimentDate = date.atStartOfDay();
            } catch (Exception e) {
                // If parsing fails, set to current date
                this.experimentDate = LocalDateTime.now();
            }
        } else {
            this.experimentDate = LocalDateTime.now();
        }
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<ExperimentImageDTO> getImages() {
        return images;
    }
    
    public void setImages(List<ExperimentImageDTO> images) {
        this.images = images;
    }
} 