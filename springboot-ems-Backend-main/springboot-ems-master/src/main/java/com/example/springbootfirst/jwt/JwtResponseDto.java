package com.example.springbootfirst.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String role;
    private String password;


    public void setToken(String token) {
        this.accessToken = token;
    }

    public String getToken() {
        return accessToken;
    }
}
