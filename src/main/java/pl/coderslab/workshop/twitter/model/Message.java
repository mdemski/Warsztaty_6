package pl.coderslab.workshop.twitter.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="USER_SENDER_ID")
    private User sender;
//    @ManyToOne
//    @JoinColumn//(name="USER_RECIPIENT_ID")
//    private User recipient;
    private String title;
    private String content;
    private LocalDateTime post;
    private boolean read;

    public Message(User sender, User recipient, String title, String content, LocalDateTime post, boolean read) {
        this.sender = sender;
//        this.recipient = recipient;
        this.title = title;
        this.content = content;
        this.post = post;
        this.read = read;
    }

    public Message() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

//    public User getRecipient() {
//        return recipient;
//    }

//    public void setRecipient(User recipient) {
//        this.recipient = recipient;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPost() {
        return post;
    }

    public void setPost(LocalDateTime post) {
        this.post = post;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
