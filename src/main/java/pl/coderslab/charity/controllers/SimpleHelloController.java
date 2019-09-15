package pl.coderslab.charity.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleHelloController {

    @GetMapping("/admin")
    public String hello(){
        return "Hello there admin";
    }
}
