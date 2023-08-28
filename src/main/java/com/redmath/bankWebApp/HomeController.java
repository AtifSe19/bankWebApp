package com.redmath.bankWebApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String hello(){
        return "Welcome to our bank app!";
    }
}
