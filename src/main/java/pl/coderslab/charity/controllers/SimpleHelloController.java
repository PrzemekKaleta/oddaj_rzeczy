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


    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/user")
    public String hello2(){
        return "Hello there user";
    }


    @GetMapping("/all")
    public String hello3(){
        return "Hello there logged";
    }

    @GetMapping("/all2")
    public String hello4(){
        return "Hello there logged, all 2";
    }

}
