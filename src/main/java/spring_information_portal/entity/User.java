package spring_information_portal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String mail;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Comment> comments;

    public User() {
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public User(String login, String password, String mail, UserRole role) {
        this();
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
    }

    public User(String login, String password, String mail, UserRole role, List<Post> posts, List<Comment> comments) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.posts = posts;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
