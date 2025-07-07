package com.example.Springbootfirst.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee
{
    private int empid;
    private String name;
    private String job;


    public Employee() {

    }
}
