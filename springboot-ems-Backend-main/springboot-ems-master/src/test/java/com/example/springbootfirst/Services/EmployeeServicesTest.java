package com.example.springbootfirst.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailsRepositary;
import com.example.springbootfirst.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class EmployeeServicesTest {

    @Mock
    RegisterDetailsRepositary registerDetailsRepositary ;

    @InjectMocks
    EmployeeService employeeService;
                                                 ROLL NO:23ME026
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testgetmethod(){
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(registerDetailsRepositary.findAll()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeService.getMethod();
        assertEquals(2, result.size());

    }

}
