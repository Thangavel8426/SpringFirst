package com.lablogbook.backend.service;

import com.lablogbook.backend.dto.ExperimentDTO;
import com.lablogbook.backend.entity.Experiment;
import com.lablogbook.backend.entity.ExperimentStatus;
import com.lablogbook.backend.entity.User;
import com.lablogbook.backend.repository.ExperimentRepository;
import com.lablogbook.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExperimentService {
    
    @Autowired
    private ExperimentRepository experimentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<ExperimentDTO> getExperimentsByUsername(String username) {
        return experimentRepository.findByUser_Username(username).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Page<ExperimentDTO> getAllExperiments(Pageable pageable) {
        return experimentRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    public Optional<ExperimentDTO> getExperimentById(Long id, String username) {
        return experimentRepository.findById(id)
                .filter(experiment -> experiment.getUser().getUsername().equals(username))
                .map(this::convertToDTO);
    }
    
    public List<ExperimentDTO> getExperimentsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return experimentRepository.findByUser(user, Pageable.unpaged()).getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Page<ExperimentDTO> getExperimentsByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return experimentRepository.findByUser(user, pageable)
                .map(this::convertToDTO);
    }
    
    public List<ExperimentDTO> getExperimentsByStatus(ExperimentStatus status) {
        return experimentRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Page<ExperimentDTO> searchExperiments(String keyword, Pageable pageable) {
        return experimentRepository.findByKeyword(keyword, pageable)
                .map(this::convertToDTO);
    }
    
    public ExperimentDTO createExperiment(ExperimentDTO experimentDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Experiment experiment = new Experiment();
        experiment.setTitle(experimentDTO.getTitle());
        experiment.setObjective(experimentDTO.getObjective());
        experiment.setHypothesis(experimentDTO.getHypothesis());
        experiment.setMaterials(experimentDTO.getMaterials());
        experiment.setProcedureSteps(experimentDTO.getProcedureSteps());
        experiment.setObservations(experimentDTO.getObservations());
        experiment.setResults(experimentDTO.getResults());
        experiment.setConclusion(experimentDTO.getConclusion());
        experiment.setStatus(experimentDTO.getStatus() != null ? experimentDTO.getStatus() : ExperimentStatus.DRAFT);
        experiment.setUser(user);
        experiment.setExperimentDate(convertToLocalDateTime(experimentDTO.getExperimentDate()));
        
        Experiment savedExperiment = experimentRepository.save(experiment);
        return convertToDTO(savedExperiment);
    }
    
    public ExperimentDTO updateExperiment(Long id, ExperimentDTO experimentDTO, String username) {
        Experiment experiment = experimentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiment not found"));
        
        if (!experiment.getUser().getUsername().equals(username)) {
            throw new RuntimeException("User not authorized to update this experiment");
        }
        
        experiment.setTitle(experimentDTO.getTitle());
        experiment.setObjective(experimentDTO.getObjective());
        experiment.setHypothesis(experimentDTO.getHypothesis());
        experiment.setMaterials(experimentDTO.getMaterials());
        experiment.setProcedureSteps(experimentDTO.getProcedureSteps());
        experiment.setObservations(experimentDTO.getObservations());
        experiment.setResults(experimentDTO.getResults());
        experiment.setConclusion(experimentDTO.getConclusion());
        experiment.setStatus(experimentDTO.getStatus());
        experiment.setExperimentDate(convertToLocalDateTime(experimentDTO.getExperimentDate()));
        
        Experiment savedExperiment = experimentRepository.save(experiment);
        return convertToDTO(savedExperiment);
    }
    
    public void deleteExperiment(Long id, String username) {
        Experiment experiment = experimentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiment not found with id: " + id));

        if (!experiment.getUser().getUsername().equals(username)) {
            throw new RuntimeException("User not authorized to delete this experiment");
        }

        experimentRepository.deleteById(id);
    }
    
    public ExperimentDTO updateExperimentStatus(Long id, ExperimentStatus status) {
        Experiment experiment = experimentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiment not found"));
        
        experiment.setStatus(status);
        Experiment savedExperiment = experimentRepository.save(experiment);
        return convertToDTO(savedExperiment);
    }
    
    private ExperimentDTO convertToDTO(Experiment experiment) {
        ExperimentDTO dto = new ExperimentDTO();
        dto.setId(experiment.getId());
        dto.setTitle(experiment.getTitle());
        dto.setObjective(experiment.getObjective());
        dto.setHypothesis(experiment.getHypothesis());
        dto.setMaterials(experiment.getMaterials());
        dto.setProcedureSteps(experiment.getProcedureSteps());
        dto.setObservations(experiment.getObservations());
        dto.setResults(experiment.getResults());
        dto.setConclusion(experiment.getConclusion());
        dto.setStatus(experiment.getStatus());
        dto.setUserId(experiment.getUser().getId());
        dto.setUserFullName(experiment.getUser().getFirstName() + " " + experiment.getUser().getLastName());
        dto.setExperimentDate(experiment.getExperimentDate());
        dto.setCreatedAt(experiment.getCreatedAt());
        dto.setUpdatedAt(experiment.getUpdatedAt());
        return dto;
    }
    
    private LocalDateTime convertToLocalDateTime(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime;
        }
        return LocalDateTime.now();
    }
} 