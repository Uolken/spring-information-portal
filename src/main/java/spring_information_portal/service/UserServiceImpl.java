package spring_information_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_information_portal.entity.User;
import spring_information_portal.repos.UserRepos;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepos userRepos;

    @Override
    public User getById(Long id) {
        return userRepos.getById(id);
    }

    @Override
    public User getByLogin(String login) {
        return userRepos.getByLogin(login);
    }

    @Override
    public User getByMail(String mail) {
        return userRepos.getByMail(mail);
    }

    @Override
    public void saveUser(User user) {
        userRepos.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepos.save(user);
    }
}
