package pl.coderslab.workshop.twitter.dto;

import java.time.LocalDateTime;

public class CommentDTO {

    private String userName;
    private String tweetName;
    private LocalDateTime created;
    private String text;

    public CommentDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTweetName() {
        return tweetName;
    }

    public void setTweetName(String tweetName) {
        this.tweetName = tweetName;
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
