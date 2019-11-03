package spring_information_portal.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.Tag;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepos extends CrudRepository<Post, Long> {
    public Post getById(Long id);
    public Collection<Post> getByAuthorId(Long authorId);
    public List<Post> findAll();
    @Query(value="SELECT * FROM Post ORDER BY Post.date desc limit ?1,  ?2", nativeQuery = true)
    public Collection<Post> findOffsetLimit(int offset, int limit);

    public Collection<Post> findByNameIgnoreCaseContainingAndTags_NameInOrderByDateDesc(String requestString, List<String> tagNames);
    public Collection<Post> findByTags_NameInOrderByDateDesc(List<String> tagNames);
    public Collection<Post> findByNameIgnoreCaseContainingOrderByDateDesc(String requestString);
}
