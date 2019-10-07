package spring_information_portal.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring_information_portal.entity.User;

@Repository
public interface UserRepos extends CrudRepository<User, Long> {
    public User getById(Long id);
    public User getByLogin(String login);
    public User getByMail(String mail);
}
