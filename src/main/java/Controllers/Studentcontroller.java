package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Student;
import com.example.springbootfirst.models.Studentparentdetails;
import com.example.springbootfirst.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService st;


    @GetMapping("/")
    public List<Student> Studentdetails() {
        return st.getStudentdetails();
    }

    @GetMapping("/parents")
    public List<Studentparentdetails> StudentParentsdetails() {
        return st.getStudentparentdetails();
    }

}