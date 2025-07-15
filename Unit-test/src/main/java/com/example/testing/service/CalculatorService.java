package com.example.testing.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public String welcome(){
        return "Welcome to calculator testing";
    }

    public int add(int num1, int num2) {
        return num1+num2;
    }

    public double sub(double num1, double num2) {
        return num1 - num2;
    }

    public long mul(int num1, int num2) {
        return num1*num2;
    }

    public double div(double num1, double num2) {
        return num1!=0 && num2!=0?(num1>num2?num1/num2:num2/num1):0.0;
    }
}
