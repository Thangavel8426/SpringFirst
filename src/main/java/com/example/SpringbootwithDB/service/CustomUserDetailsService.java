package com.example.SpringbootwithDB.service;

import com.example.SpringbootwithDB.models.RegisterDetails;
import com.example.SpringbootwithDB.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;
    /*
       1.loading data from your database
       2.Setting up the authorities
       3.returning up proper UserDetails
           */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Step 1: Load user from DB
        RegisterDetails user = registerDetailsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Step 2: Convert roles to GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toSet());

        // Step 3: Return Spring Security User
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
