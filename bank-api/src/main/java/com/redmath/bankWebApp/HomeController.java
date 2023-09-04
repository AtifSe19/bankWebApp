package com.redmath.bankWebApp;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String welcome(Authentication auth){
        return "Welcome to our Bank web app (backend)!";
    }
}
