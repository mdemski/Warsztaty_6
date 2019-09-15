package pl.coderslab.workshop.twitter.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name="TWEET_ID")
    private Tweet tweet;
    private LocalDateTime created;
    private String text;

    public Comment(User user, Tweet tweet, LocalDateTime created, String text) {
        this.user = user;
        this.tweet = tweet;
        this.created = created;
        this.text = text;
    }

    public Comment() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
