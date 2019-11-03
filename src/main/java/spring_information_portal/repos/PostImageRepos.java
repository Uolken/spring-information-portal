package spring_information_portal.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_information_portal.entity.PostImage;

@Repository
public interface PostImageRepos extends CrudRepository<PostImage, Long> {

}
