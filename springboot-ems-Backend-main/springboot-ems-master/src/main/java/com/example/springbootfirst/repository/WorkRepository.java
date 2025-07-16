package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Integer> {
    Optional<Work> findByDescription(String description);
}
