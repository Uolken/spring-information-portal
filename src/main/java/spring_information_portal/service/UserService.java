package spring_information_portal.service;

import spring_information_portal.entity.User;

public interface UserService {
    public User getById(Long id);
    public User getByLogin(String login);
    public User getByMail(String mail);

    public void saveUser(User user);
    public void updateUser(User user);

}
