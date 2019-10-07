package spring_information_portal.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_information_portal.entity.Post;

import java.util.Collection;

@Repository
public interface PostRepos extends CrudRepository<Post, Long> {
    public Post getById(Long id);
    public Collection<Post> getByAuthorId(Long authorId);
    public Collection<Post> findAll();
}
