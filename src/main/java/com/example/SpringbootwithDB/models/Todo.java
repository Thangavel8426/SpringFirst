package com.example.SpringbootwithDB.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String task;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private RegisterDetails employee;
}

