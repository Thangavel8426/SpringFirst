package com.example.springbootfirst.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_name",nullable = false,unique = true)
    private String username;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns =
    @JoinColumn(name = "user_id",referencedColumnName = "empId"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "roleId")
    )
    private Set<Roles> roles;



}