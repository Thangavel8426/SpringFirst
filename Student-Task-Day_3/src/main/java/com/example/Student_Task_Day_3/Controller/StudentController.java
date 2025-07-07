package com.example.Student_Task_Day_3.Controller;

import com.example.Student_Task_Day_3.Models.Student;
import com.example.Student_Task_Day_3.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> getStudentDetails(){
        return studentService.getStudentDetails();
    }

    @GetMapping("/{rollNo}")
    public Student getStudentById(@PathVariable int rollNo){
        return studentService.getStudentById(rollNo);
    }

    @PostMapping
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{rollNo}")
    public String deleteString(@PathVariable int rollNo){
        return studentService.deleteStudent(rollNo);
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

}
