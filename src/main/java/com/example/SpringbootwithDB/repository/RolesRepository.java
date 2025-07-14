package com.example.SpringbootwithDB.repository;

import com.example.SpringbootwithDB.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(String roleName); //
}

