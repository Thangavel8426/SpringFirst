package com.example.SpringbootwithDB.repository;

import com.example.SpringbootwithDB.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails,Integer> {
    RegisterDetails findByEmail(String email);

    Optional<RegisterDetails> findByUsername(String username);

    List<RegisterDetails> findByRoles_RoleName(String roleName);
}
