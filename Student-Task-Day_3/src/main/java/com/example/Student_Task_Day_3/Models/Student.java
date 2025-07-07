package com.example.Student_Task_Day_3.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int rollNumber;
    private String name;
    private int age;
    private String mobileNumber;
}
