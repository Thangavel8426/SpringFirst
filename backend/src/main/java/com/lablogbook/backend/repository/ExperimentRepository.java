package com.lablogbook.backend.repository;

import com.lablogbook.backend.entity.Experiment;
import com.lablogbook.backend.entity.ExperimentStatus;
import com.lablogbook.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long> {
    Page<Experiment> findByUser(User user, Pageable pageable);
    List<Experiment> findByUserAndStatus(User user, ExperimentStatus status);
    List<Experiment> findByStatus(ExperimentStatus status);
    
    @Query("SELECT e FROM Experiment e WHERE e.user = :user AND e.experimentDate BETWEEN :startDate AND :endDate")
    List<Experiment> findByUserAndDateRange(@Param("user") User user, 
                                           @Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT e FROM Experiment e WHERE e.title LIKE %:keyword% OR e.objective LIKE %:keyword%")
    Page<Experiment> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    List<Experiment> findByUser_Username(String username);
} 