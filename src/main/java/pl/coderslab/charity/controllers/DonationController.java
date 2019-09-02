package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;

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

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/1")
    String getStep1(Model model){

        List<String> allNames = categoryRepository.findAllNames();
        model.addAttribute("allNames", allNames);
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

}
