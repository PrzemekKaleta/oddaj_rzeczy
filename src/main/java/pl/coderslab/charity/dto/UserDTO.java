package pl.coderslab.charity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    @NotNull
    @NotEmpty
    @Email(message = "niepoprawny adres email")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 30, message="hasło musi mieć od 4 do 30 znaków")
    private String password;

    private String matchingPassword;

}
