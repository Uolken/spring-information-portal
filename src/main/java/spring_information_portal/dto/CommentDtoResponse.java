package spring_information_portal.dto;

import spring_information_portal.entity.Comment;
import spring_information_portal.entity.User;
import spring_information_portal.tool.DateTranslator;

import java.util.Date;

public class CommentDtoResponse {
    private Long id;
    private String author;
    private String text;
    private String date;
    private Long rate;
    public Boolean isRated;

    public CommentDtoResponse(Comment comment, User user) {
        this.id = comment.getId();
        this.author = comment.getAuthor().getLogin();
        this.text = comment.getText();
        this.date = DateTranslator.toTimeAgo((new Date().getTime() - comment.getDate().getTime())/1000);
        this.rate = comment.getRate();
        this.isRated = false;
        if (user!=null){
            for(User rUser: comment.getUserRate()){
                if (rUser.getId().equals( user.getId())) {
                    this.isRated = true;
                    break;
                }
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Boolean isRated() {
        return isRated;
    }

    public void setRated(Boolean rated) {
        isRated = rated;
    }
}
