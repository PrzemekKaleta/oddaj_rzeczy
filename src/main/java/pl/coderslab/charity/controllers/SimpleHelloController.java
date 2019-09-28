package pl.coderslab.charity.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleHelloController {

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String hello(){
        return "Hello there admin";
    }
}
