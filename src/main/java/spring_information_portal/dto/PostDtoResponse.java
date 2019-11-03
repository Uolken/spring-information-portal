package spring_information_portal.dto;

import spring_information_portal.entity.Comment;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.Tag;
import spring_information_portal.entity.User;
import spring_information_portal.tool.DateTranslator;

import java.util.Collection;
import java.util.Date;

public class PostDtoResponse {
    private Long id;

    private String name;

    private User author;

    private String date;

    private Long rate;

    private Collection<Tag> tags;

    private Collection<Comment> comments;

    private String text;

    public boolean isRated;

    private Long imgId;

    public PostDtoResponse(Post post, User user) {
        this.id = post.getId();
        this.name = post.getName();
        this.author = post.getAuthor();

        this.rate = post.getRate();
        this.tags = post.getTags();
        this.comments = post.getComments();
        this.text = post.getText();
        this.isRated = false;
        this.imgId = post.getImgId();
        this.date = DateTranslator.toTimeAgo((new Date().getTime() - post.getDate().getTime())/1000);

        if (user!=null){
            for(User rUser: post.getUserRate()){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }
}
