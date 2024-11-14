package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/custom-login")
    public String login() {
        return "custom-login";  // Return the name of the custom-login.html template
    }
}