package com.example.springbootfirst.Controller;

import com.example.springbootfirst.controllers.EmployeeController;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    AuthService authService;


    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoute() {
        String result = employeeController.route();
        assertEquals("springboot", result);
    }

    @Test
    void testGetMethod() {
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(authService.getMethod()).thenReturn(Arrays.asList(emp1, emp2));

        List<RegisterDetails> result = employeeController.getMethod();
        assertEquals(2, result.size());
    }

                                                   ROLL NO:23ME026
    @Test
    void testGetEmployeeById() {
        RegisterDetails emp = new RegisterDetails();
        when(authService.getRegisterById(1)).thenReturn(emp);

        RegisterDetails result = employeeController.getEmployeeById(1);
        assertEquals(emp, result);
    }

    @Test
    void testPostMethod() {
        RegisterDetails emp = new RegisterDetails();
        when(authService.saveRegister(emp)).thenReturn("Saved");

        String result = employeeController.postMethod(emp);
        assertEquals("Saved", result);
    }

    //    23CC046 - SANJITH S
    @Test
    void testPutMethod() {
        RegisterDetails emp = new RegisterDetails();
        when(authService.updateRegister(emp)).thenReturn("Updated");

        String result = employeeController.putMethod(emp);
        assertEquals("Updated", result);
    }

    @Test
    void testDeleteMethod() {
        when(authService.deleteRegisterById(1)).thenReturn("Deleted");

        String result = employeeController.deleteMethod(1);
        assertEquals("Deleted", result);
    }

    @Test
    void testGetUsersByRole() {
        RegisterDetails emp = new RegisterDetails();
        when(authService.getUsersByRole("ROLE_USER")).thenReturn(Collections.singletonList(emp));

        List<RegisterDetails> result = employeeController.getUsersByRole("ROLE_USER");
        assertEquals(1, result.size());
    }

}
