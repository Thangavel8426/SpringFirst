//package com.example.springbootfirst.services;
//
//import com.example.springbootfirst.models.RegisterDetails;
//import com.example.springbootfirst.repository.RegisterDetailsRepositary;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsServices implements UserDetailsService {
//    @Autowired
//    RegisterDetailsRepositary registerDetailsRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        RegisterDetails user = registerDetailsRepository.findByUsername(username)                .orElseThrow(()->new RuntimeException("User Not Found"));
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName()))
//                .collect(Collectors.toSet());
//        System.out.println("Username is " + user.getUsername() + "\nPassword is " + user.getPassword() + "\nAuthority is " + authorities);
//        return new User(user.getUsername(), user.getPassword() ,authorities);
//    }
//
//}