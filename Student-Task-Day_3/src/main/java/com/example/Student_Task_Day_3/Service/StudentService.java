package com.example.Student_Task_Day_3.Service;
import com.example.Student_Task_Day_3.Models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    List<Student> studentDetails = new ArrayList<>(
            Arrays.asList(new Student(101 , "sridhar", 20, "9874646230"),
                    new Student(145,"sabari", 19,  "8465656556"))
    );

    public List<Student> getStudentDetails(){
        return studentDetails;
    }

    public String studentName(){
        return "Student name is : Praveen";
    }

    public String studentAge(){
        return "Student Age is : 20";
    }

    public String studentMobileNumber(){
        return "Student Mobile Number is : 9014718812";
    }

    public Student getStudentById(int rollNo){
        int ind = 0;
        boolean flag = false;
        for(int i=0;i<studentDetails.size();i++){
            if(rollNo == studentDetails.get(i).getRollNumber()){
                ind = i;
                flag = true;
                break;
            }
        }

        if(flag){
            return studentDetails.get(ind);
        }
        else{
            return new Student();
        }
    }

    public String createStudent(Student student){
        studentDetails.add(student);

        return "student details is added successfully";
    }

    public String deleteStudent(int rollNo){
        int ind = 0;
        boolean flag = false;

        for(int i=0;i<studentDetails.size();i++){
            if(rollNo == studentDetails.get(i).getRollNumber()){
                ind = i;
                flag = true;
                break;
            }
        }

        if(flag){
            studentDetails.remove(ind);
            return "student is deleted succesfull";
        }
        else{
            return "Enter the correct rollnumber";
        }
    }

    public String updateStudent(Student student){
        int ind = 0;
        boolean flag = false;
        for(int i=0;i<studentDetails.size();i++){
            if(student.getRollNumber() == studentDetails.get(i).getRollNumber()){
                flag = true;
                ind = i;
                break;
            }
        }

        if(flag){
            studentDetails.set(ind, student);
            return "Student is Updated sucessfull";
        }
        else{
            return "Enter the correct RollNumber";
        }
    }
}
