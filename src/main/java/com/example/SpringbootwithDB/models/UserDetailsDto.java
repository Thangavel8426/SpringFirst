package com.example.SpringbootwithDB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private int empId;
    private String Name;
    private String email;
    private String Password;
    private String Username;
    private Set<String> RoleName;


}
