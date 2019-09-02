package pl.coderslab.workshop.twitter.dto;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.workshop.twitter.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TweetDTO {

    private String userName;
    @NotBlank
    @Size(max = 160, message = "Maksymalna długość wpisu to 160 znaków")
    private String text;
    private LocalDateTime created;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
