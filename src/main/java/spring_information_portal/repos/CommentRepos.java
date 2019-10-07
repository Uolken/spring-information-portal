package spring_information_portal.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_information_portal.entity.Comment;

@Repository
public interface CommentRepos extends CrudRepository<Comment, Long> {
}
