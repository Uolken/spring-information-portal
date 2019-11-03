package spring_information_portal.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_information_portal.entity.Tag;

import java.util.Collection;
import java.util.List;

@Repository
public interface TagRepos extends CrudRepository<Tag, Long> {
    public Tag getByName(String name);
    public Tag getById(long id);
    public Collection<Tag> getByNameIn(List<String> names);
}
