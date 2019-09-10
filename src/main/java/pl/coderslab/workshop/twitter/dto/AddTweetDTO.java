package pl.coderslab.workshop.twitter.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class AddTweetDTO {
    @NotBlank
    @Size(max = 160, message = "Maksymalna długość wpisu to 160 znaków")
    private String text;

    public AddTweetDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
