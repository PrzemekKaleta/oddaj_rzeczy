package pl.coderslab.charity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.*;


@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
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

    @PostMapping("register")
    public String register(@Valid UserDTO userDTO, BindingResult result, Model model){

        if(result.hasErrors()){
            System.out.println("not pass");
            model.addAttribute("errors", result);
            return "register";
        }

        if(!userDTO.getPassword().equals(userDTO.getMatchingPassword())){
            result.addError(new FieldError("userDTO", "matchingPassword", "źle powtórzone hasło"));
            return "register";
        }

        if(userRepository.existsByUsername(userDTO.getUsername())) {

            System.out.println(userRepository.existsByUsername(userDTO.getUsername()) + " " + userDTO.getUsername());
            result.addError(new FieldError("userDTO", "username", "email jest już zajęty"));
            return "register";
        }

        System.out.println("before save user");

        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());



        Optional<Role> role = roleRepository.findById(2L);


        /*    private String saveTrim(final Optional<String> input) {
        if(input.isPresent()) {
            return input.get().trim();
        }

        return "";
    }*/

    /*        Optional<Product> getValueBy(final String id) {
            if (StringUtils.isBlank(id)) {
                return Optional.empty();
            }
            return Optional.ofNullable(readFromDB(id));
        }*/

        if(role.isEmpty()){
            role = null;
        }

        HashSet<Optional<Role>> roles = new HashSet<>();





        roles.add(role);
        user.setRoles(roles);



/*        public Collection<? extends GrantedAuthority> getAuthorities() {

            return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                    .collect(Collectors.toList());
        }*/

        /*Role roleUser = roleRepository.findByRole("USER");

        System.out.println(roleUser.toString());

        Set<Role> roles = new HashSet<Role>();
        roles.add(roleUser);
        user.setRoles(roles);*/

        //userRepository.save(user);

        userRepository.save(user);

        System.out.println("after save user");

    /*    Role role = new Role(REFERRING_PHYSICIAN);
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);*/




/*
        user.setRoles(Arrays.asList("ROLE_USER"));
        return repository.save(user);*/

        System.out.println("pass");

        return "redirect:/";

    }


}
