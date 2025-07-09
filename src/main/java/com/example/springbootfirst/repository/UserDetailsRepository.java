package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
