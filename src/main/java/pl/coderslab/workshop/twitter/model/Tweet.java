package pl.coderslab.workshop.twitter.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tweets")
public class Tweet extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
    private String text;
    private LocalDateTime created;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tweet")
    private List<Comment> comments;

    public Tweet(User user, String text, LocalDateTime created, List<Comment> comments) {
        this.user = user;
        this.text = text;
        this.created = created;
        this.comments = comments;
    }

    public Tweet() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

//    @Override
//    public String toString() {
//        return "Tweet{" +
//                "user=" + user +
//                ", text='" + text + '\'' +
//                ", created=" + created +
//                "} " + super.toString();
//    }
}
