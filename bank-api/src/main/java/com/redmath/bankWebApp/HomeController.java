package com.redmath.bankWebApp;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("https://localhost:3000")
public class HomeController {

    @GetMapping("/welcome")
    public String welcome(Authentication auth){
        return "Welcome to our Bank web app (backend)!";
    }
}
