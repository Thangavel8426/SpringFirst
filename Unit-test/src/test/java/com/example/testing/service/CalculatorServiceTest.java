package com.example.testing.service;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class CalculatorServiceTest {
    private CalculatorService calculatorService = new CalculatorService();

    @Test
    void welcome(){
        assertThat(calculatorService.welcome()).isEqualTo("Welcome to calculator testing");
    }

    @Test
    void add(){
        assertThat(calculatorService.add(3, 5) == 8);
    }
}
