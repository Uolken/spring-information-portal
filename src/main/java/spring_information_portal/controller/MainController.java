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
import spring_information_portal.service.PostService;
import spring_information_portal.service.UserService;
import spring_information_portal.tool.UserValidator;


@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/home")
    String getPage(Model model){
        model.addAttribute("posts", postService.getAll());
        return "home";
    }


    @GetMapping("/login")
    String getLoginPage(Model model, @RequestParam(required = false) Boolean error){
        if (Boolean.TRUE.equals(error))
            model.addAttribute("error", true);
        else
            model.addAttribute("error", false);
        return "login";
    }

    @GetMapping("/sign_up")
    String getSignUpPage(Model model, @RequestParam(required = false) Byte error){
        model.addAttribute("error", error);

        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasFieldErrors("mail"))
            return "redirect:sign_up?error=1";
        else
            if (result.hasFieldErrors("login"))
                return "redirect:sign_up?error=2";

        userService.saveUser(user);
        return "redirect:/home";
    }

}
