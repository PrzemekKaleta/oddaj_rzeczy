package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

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
        return "form";
    }

    @PostMapping("")
    String postForm(@ModelAttribute ("donation") @Valid Donation donation, BindingResult result, Model model){
        if(result.hasErrors()){
            return "form";
        }
        donationRepository.save(donation);
        model.addAttribute("message", donation.toString());
        return "simple-ok";
    }

    @ModelAttribute("categoryList")
    public Collection<Category> categories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    @ModelAttribute("institutionsList")
    public Collection<Institution> institutionsList(){
        List<Institution> institutionsList = institutionRepository.findAll();
        return institutionsList;
    }

}
