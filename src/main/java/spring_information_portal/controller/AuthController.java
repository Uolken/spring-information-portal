package spring_information_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring_information_portal.entity.User;
import spring_information_portal.entity.UserRole;
import spring_information_portal.service.UserService;
import spring_information_portal.tool.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    UserService userService;

    UserValidator userValidator;

    @GetMapping("/login")
    String getLoginPage(Model model, @RequestParam(required = false) Boolean error){
        model.addAttribute("isLogin", false);
        model.addAttribute("currentPage", "login");

        model.addAttribute("error", Boolean.TRUE.equals(error));

        return "login";
    }

    @GetMapping("/sign_up")
    String getSignUpPage(Model model, @RequestParam(required = false) Byte error){
        model.addAttribute("isLogin", false);
        model.addAttribute("currentPage", "sign_up");

        model.addAttribute("error", error);

        return "sign_up";
    }



    @PostMapping("/sign_up")
    public String signUp(HttpServletRequest request, @ModelAttribute User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasFieldErrors("mail"))
            return "redirect:sign_up?error=1";
        else
        if (result.hasFieldErrors("login"))
            return "redirect:sign_up?error=2";
        user.setRole(UserRole.USER);
        userService.saveUser(user);
        try {
            request.login(user.getLogin(), user.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/home";
    }
    @Autowired
    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
}
