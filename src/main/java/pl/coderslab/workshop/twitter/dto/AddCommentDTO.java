package pl.coderslab.workshop.twitter.dto;

public class AddCommentDTO {

    private String comment;

    public AddCommentDTO(String comment) {
        this.comment = comment;
    }

    public AddCommentDTO() {
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
