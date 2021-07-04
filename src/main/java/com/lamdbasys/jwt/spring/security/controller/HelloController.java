package com.lamdbasys.jwt.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    @GetMapping("/{name}")
    public String greeting(@PathVariable("name") final String name) {
        return String.format("Hello, %s!", name);
    }

}
