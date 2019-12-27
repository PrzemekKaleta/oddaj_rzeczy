package pl.coderslab.charity.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){

        List<Institution> institutionList = institutionRepository.findAll();
        model.addAttribute("institutionList", institutionList);

        int quantityOfBags = donationRepository.allQuantityOfBags();
        model.addAttribute("quantityOfBags", quantityOfBags);

        int allQuantityOfSuportedInstitutions = donationRepository.allQuantityOfSuportedInstitutions();
        model.addAttribute("allQuantityOfSuportedInstitutions", allQuantityOfSuportedInstitutions);

        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/register")
    public String registerForm(WebRequest request, Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);

        return "register";
    }


/*    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "user/form";
            //jezęli ma błędy to wraca do formularza
        }

        if(userRepository.existsUserByEmail(user.email)){
            result.addError(new FieldError("user", "email", "email już jest zajęty"));
            return "user/form";
        }*/


    @PostMapping("register")
    public String register(@Valid UserDTO userDTO, BindingResult result, Model model){

        if(result.hasErrors()){
            System.out.println("not pass");
            model.addAttribute("errors", result);
            return "register";
        }

        if(!userDTO.getPassword().equals(userDTO.getMatchingPassword())){
            System.out.println("not the same password");
            result.addError(new FieldError("userDTO", "matchingPassword", "źle powtórzone hasło"));
            //model.addAttribute("errors", result);
            return "register";
        }


        System.out.println("pass");

        return "redirect:/";

    }


}
