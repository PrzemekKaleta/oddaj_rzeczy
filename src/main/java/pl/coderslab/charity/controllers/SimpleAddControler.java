package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleAddControler {

    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;

    public SimpleAddControler(CategoryRepository categoryRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }

    @RequestMapping("/simple")
    public String simple(){
        System.out.println("simple");
        return "simple";
    }

    @RequestMapping("/simple-category")
    public String simpleCategory(){
        System.out.println("category");

        Long quantity = categoryRepository.count();

        Category category = new Category();
        category.setName("category " + quantity);
        categoryRepository.save(category);

        return "simple";
    }

    @RequestMapping("/simple-donation")
    public String simpleDonation(){
        System.out.println("donation");

        Long quantity = donationRepository.count();

        Donation donation = new Donation();

        Category category1 = categoryRepository.getOne(1L);
        Category category2 = categoryRepository.getOne(2L);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);

        donation.setCategories(categoryList);
        donation.setCity("Gliwice" + quantity);
        donation.setInstitution(institutionRepository.getOne(3L));
        donation.setPickUpComment("pic up comment " + quantity);
        donation.setPickUpDate(LocalDate.now());
        donation.setPickUpTime(LocalTime.now());
        donation.setQuantity(3);
        donation.setStreet("gliwicka" + quantity + "/" + quantity*2);
        donation.setZipCode("12-345");

        donationRepository.save(donation);


        return "simple";
    }

    @RequestMapping("/simple-institution")
    public String simpleInstitution(){
        System.out.println("institution");

        Long quantity = institutionRepository.count();

        Institution institution = new Institution();
        institution.setName("institution " + quantity);
        institution.setDescription("simple description " + quantity);

        institutionRepository.save(institution);

        return "simple";
    }


}
