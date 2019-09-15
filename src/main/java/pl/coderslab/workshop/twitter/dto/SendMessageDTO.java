package pl.coderslab.workshop.twitter.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class SendMessageDTO {

    @NotBlank(message = "Wprowadź odbiorcę wiadomości")
    private String recipientName;
    @NotBlank(message = "Tytuł nie może być pusty")
    private String title;
    @Size(max = 500, message = "Maksymalna długość wiadomości to 500 znaków")
    private String content;

    public SendMessageDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}
