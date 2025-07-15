package com.example.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.testing.service.CalculatorService;
@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/")
    String welcome(){
        return calculatorService.welcome();
    }

    @PostMapping("/add")
    int add(@RequestParam int num1, @RequestParam int num2){
        return calculatorService.add(num1, num2);
    }

    @PostMapping("/sub")
    double sub(@RequestParam double num1, @RequestParam double num2){
        return calculatorService.sub(num1, num2);
    }

    @PostMapping("/mul")
    long mul(@RequestParam int num1, @RequestParam int num2){
        return calculatorService.mul(num1, num2);
    }

    @PostMapping("/div")
    double div(@RequestParam double num1, @RequestParam double num2){
        return calculatorService.div(num1, num2);
    }
}
