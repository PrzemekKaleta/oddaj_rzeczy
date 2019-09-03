package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@ToString
@Entity
@Getter
@Setter
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Range(min=1, max=100)
    private int quantity;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    private Institution institution;

    //@NotBlank
    private String street;

    //@NotBlank
    private String city;

    //@NotBlank
    private String zipCode;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime pickUpTime;

    private String pickUpComment;

}
