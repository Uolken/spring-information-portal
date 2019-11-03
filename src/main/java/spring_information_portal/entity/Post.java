package spring_information_portal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    private Date date;

    private Long rate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "post_tag",
            uniqueConstraints={
                    @UniqueConstraint(columnNames = {"post_id", "tag_id"})
            },
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Collection<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Collection<Comment> comments;

    private String text;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "post_rated_by_user",
            uniqueConstraints={
                    @UniqueConstraint(columnNames = {"post_id", "user_id"})
            },
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userRate;

    private Long imgId;


    public Post() {
        this.comments = new ArrayList<Comment>();
        this.tags = new ArrayList<Tag>();
        this.rate = new Long(0);
    }

    public Post(String name, User author, Date date, Collection<Tag> tags, String text, Long imgId) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.tags = tags;
        this.text = text;
        this.rate = new Long(0);
        this.comments = new ArrayList<>();
        this.imgId = imgId;
    }

    public Post(String name, User author, Date date, String text) {
        this();
        this.name = name;
        this.author = author;
        this.date = date;
        this.text = text;
    }

    public Post(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<User> getUserRate() {
        return userRate;
    }

    public void setUserRate(Collection<User> userRate) {
        this.userRate = userRate;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }
}
