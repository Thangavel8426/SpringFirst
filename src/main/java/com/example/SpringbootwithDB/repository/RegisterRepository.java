package com.example.SpringbootwithDB.repository;

import com.example.SpringbootwithDB.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<RegisterDetails,Integer> {

    Optional<RegisterDetails> findByUsername(String username);

}
