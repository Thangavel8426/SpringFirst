package com.example.springbootfirst.services;

import com.example.springbootfirst.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Map<String, Object> authenticateAndGenerateToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = jwtTokenProvider.generateToken(authentication);
        User userPrincipal = (User) authentication.getPrincipal();
        String role = "";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            role = authority.getAuthority();
            break;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("username", userPrincipal.getUsername());
        result.put("role", role);
      //  result.put("password", password);

        return result;
    }
}
