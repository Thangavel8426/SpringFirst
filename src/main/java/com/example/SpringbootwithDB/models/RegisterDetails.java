
package com.example.SpringbootwithDB.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_details")
public class RegisterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name="User_name",nullable = false,unique = true)
    private String username;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns =
    @JoinColumn(name = "user_id",referencedColumnName = "empId"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "roleId")
    )

    private Set<Roles>roles;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Todo> todos;
}
