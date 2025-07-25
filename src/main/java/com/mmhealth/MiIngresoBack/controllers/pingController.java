package com.mmhealth.MiIngresoBack.controllers;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pingController {
    @GetMapping("/")
    public String ping(){
        return "pong";
    }
}
