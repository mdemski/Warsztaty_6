package pl.coderslab.workshop.twitter.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Tweet> tweets;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_SENDER_ID")
    private Set<Message> sendedMessages;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_RECIPIENT_ID")
    private Set<Message> receivedMessages;

    public User(String email, String password, String firstName, String lastName, List<Tweet> tweets, List<Comment> comments, List<Message> messages, Set<Message> sendedMessages, Set<Message> receivedMessages) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tweets = tweets;
        this.comments = comments;
        this.sendedMessages = sendedMessages;
        this.receivedMessages = receivedMessages;
    }

    public User() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

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

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Set<Message> getSendedMessages() {
        return sendedMessages;
    }

    public void setSendedMessages(Set<Message> sendedMessages) {
        this.sendedMessages = sendedMessages;
    }

    public Set<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}
