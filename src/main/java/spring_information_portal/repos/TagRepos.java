package spring_information_portal.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_information_portal.entity.Tag;

@Repository
public interface TagRepos extends CrudRepository<Tag, Long> {
}
