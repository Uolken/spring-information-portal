package spring_information_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.User;
import spring_information_portal.repos.PostRepos;

import java.util.Collection;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepos postRepos;

    @Override
    public Post getById(Long id) {
        return postRepos.getById(id);
    }

    @Override
    public Collection<Post> getByUser(User user) {
        return postRepos.getByAuthorId(user.getId());
    }

    @Override
    public Collection<Post> getAll() {
        return postRepos.findAll();
    }

    @Override
    public void save(Post post) {
        postRepos.save(post);
    }


}
