package com.example.cms.controller;

import com.example.cms.service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CmsController {
    @Autowired
    CmsService cmsService;

    @GetMapping("/")
    public String getStudents(){
        return "Welcome to cms ";
    }
}
