package com.lablogbook.backend.dto;

import java.time.LocalDateTime;

public class ExperimentImageDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private String description;
    private Long experimentId;
    private LocalDateTime uploadedAt;
    
    // Constructors
    public ExperimentImageDTO() {
    }
    
    public ExperimentImageDTO(Long id, String fileName, String filePath, Long experimentId) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.experimentId = experimentId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getExperimentId() {
        return experimentId;
    }
    
    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }
    
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
} 