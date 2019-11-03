package spring_information_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring_information_portal.dto.PostDtoResponse;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.User;
import spring_information_portal.service.PostService;

import java.util.ArrayList;


@Controller
public class HomeController {

    PostService postService;

    @GetMapping("/")
    String getPage(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    String getPage(Model model,  Authentication authentication){
        model.addAttribute("isLogin", (authentication != null));
        model.addAttribute("currentPage", "home");
        ArrayList<PostDtoResponse> posts = new ArrayList<>();
        User user;
        if (authentication!=null)
            user = (User) authentication.getPrincipal();
        else user = null;

        for(Post post :  postService.getAll()){


            posts.add(new PostDtoResponse(post,user));
        }
        model.addAttribute("posts", posts);
        return "home";
    }
    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }
}
