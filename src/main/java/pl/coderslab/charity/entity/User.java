package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    String email;

    @NotBlank
    @Column(nullable = false)
    String password;

    @ManyToMany
    List<Authority> authorities = new ArrayList<>();

    Boolean enabled;

}
