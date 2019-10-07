package spring_information_portal.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring_information_portal.entity.User;
import spring_information_portal.service.UserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (!MailValidator.isValid(user.getMail())) {
            errors.rejectValue(
                    "mail", "", "Bad mail"
            );
        }
        if (userService.getByLogin(user.getLogin()) != null) {
            errors.rejectValue(
                    "login", "", "This login is already in use"
            );
        }
        if (userService.getByMail(user.getMail()) != null) {
            errors.rejectValue(
                    "mail", "", "This email is already in use"
            );
        }
    }
}
