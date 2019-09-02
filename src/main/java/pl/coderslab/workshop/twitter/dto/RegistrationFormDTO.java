package pl.coderslab.workshop.twitter.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class RegistrationFormDTO {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 12)
    //TODO walidacja pod kątem równości password i rePassword
    private String password;
    @NotBlank
    @Size(min = 8, max = 12)
    private String rePassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //hash i equals jest potrzebne gdy porównujemy lub gdy przekazujemy do kolekcji np. List
}
