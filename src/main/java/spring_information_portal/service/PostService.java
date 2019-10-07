package spring_information_portal.service;

import spring_information_portal.entity.Post;
import spring_information_portal.entity.User;

import java.util.Collection;

public interface PostService {
    public Post getById(Long id);
    public Collection<Post> getByUser(User user);
    public Collection<Post> getAll();

    public void save(Post post);
}
