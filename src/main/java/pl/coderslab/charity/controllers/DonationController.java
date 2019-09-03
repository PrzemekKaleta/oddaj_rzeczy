package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("")
    String getForm(Model model){
        model.addAttribute("donation", new Donation());
        return "simple-form";
    }

    @PostMapping("")
    String postForm(@ModelAttribute Donation donation, Model model){
        donationRepository.save(donation);
        model.addAttribute("message", donation.toString());
        return "simple-ok";
    }

    @GetMapping("/1")
    String getStep1(Model model){

        List<String> allNames = categoryRepository.findAllNames();
       // model.addAttribute("allNames", allNames);
        model.addAttribute("categories", categoryRepository.findAll());

        return "form-step1";
    }

    @PostMapping("/2")
    String postDonation(HttpServletRequest request, Model model){

        String[] categoriesChosen = request.getParameterValues("categories");
        List<Long> longs = Arrays.stream(categoriesChosen).map(Long::valueOf).collect(Collectors.toList());

        model.addAttribute("idChosen", longs);

        return "form-step2";
    }

    @ModelAttribute("categories")
    public Collection<Category> categories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @ModelAttribute("institutions")
    public Collection<Institution> institutions(){
        List<Institution> institutions = institutionRepository.findAll();
        return institutions;
    }

}
