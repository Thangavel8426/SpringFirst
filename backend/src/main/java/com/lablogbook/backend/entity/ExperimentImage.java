package com.lablogbook.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "experiment_images")
public class ExperimentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String fileName;
    
    @NotBlank
    private String filePath;
    
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiment_id")
    private Experiment experiment;
    
    private LocalDateTime uploadedAt;
    
    @PrePersist
    protected void onCreate() {
        uploadedAt = LocalDateTime.now();
    }
    
    // Constructors
    public ExperimentImage() {
    }
    
    public ExperimentImage(String fileName, String filePath, Experiment experiment) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.experiment = experiment;
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
    
    public Experiment getExperiment() {
        return experiment;
    }
    
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }
    
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
} 