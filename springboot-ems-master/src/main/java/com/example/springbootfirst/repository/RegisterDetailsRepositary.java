package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RegisterDetailsRepositary extends JpaRepository<RegisterDetails,Integer> {
    RegisterDetails findByEmail(String email);
    Optional<RegisterDetails> findByUsername(String username);
    List<RegisterDetails> findByRolesRoleName(String roleName);

}
