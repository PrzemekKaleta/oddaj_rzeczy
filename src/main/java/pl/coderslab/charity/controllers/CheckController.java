package pl.coderslab.charity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/check")

public class CheckController {

    @GetMapping("/on")
    String on(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String nameFromAddress = currentPrincipalName.substring(0, currentPrincipalName.indexOf("@"));

        model.addAttribute("name_from_address", nameFromAddress);
        return "header-loged";


    }

    @GetMapping("/off")
    String off(){
        return "header-nonloged";
    }
}
