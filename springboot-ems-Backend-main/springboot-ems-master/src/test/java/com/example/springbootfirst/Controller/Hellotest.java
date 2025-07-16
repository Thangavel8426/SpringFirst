package com.example.springbootfirst.Controller;

import com.example.springbootfirst.controllers.Hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hellotest {
    public void Hellotest(){
        Hello hell = new Hello();
        String str = hell.helloTest();
        System.out.println(str);
        assertEquals("Hello test",str);

    }
}
