package com.example.springbootfirst.services;

import com.example.springbootfirst.models.Student;
import com.example.springbootfirst.models.Studentparentdetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class StudentService {
    List<Student> std = new ArrayList<>(
            Arrays.asList(new Student("padmaprashath",20,"padmaprashath@gmail.com"))
    );


    public List<Student> getStudentdetails() {
        return std;
    }
    List<Studentparentdetails> stdp = new ArrayList<>(
            Arrays.asList(new Studentparentdetails("xyz","abc"))
    );
    public List<Studentparentdetails> getStudentparentdetails() {
        return stdp;
    }

}