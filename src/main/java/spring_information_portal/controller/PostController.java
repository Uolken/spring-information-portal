package spring_information_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring_information_portal.dto.CommentDtoResponse;
import spring_information_portal.dto.PostDtoRequest;
import spring_information_portal.dto.PostDtoResponse;
import spring_information_portal.entity.Comment;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.PostImage;
import spring_information_portal.entity.User;
import spring_information_portal.service.CommentService;
import spring_information_portal.service.PostImageService;
import spring_information_portal.service.PostService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Controller
public class PostController {

    private PostService postService;

    private CommentService commentService;

    private PostImageService postImageService;

    @GetMapping("/create_post")
    String getCreatePostPage(Model model, Authentication authentication){
        model.addAttribute("isLogin",true);
        model.addAttribute("currentPage", "create_post");

        model.addAttribute("username", ((User)authentication.getPrincipal()).getLogin());

        return "create_post";
    }


    @PostMapping("/create_post")
    public String createPost(Authentication authentication, @ModelAttribute PostDtoRequest post){
        postService.createAndSave(((User)authentication.getPrincipal()).getId(), post.getName(), post.getText(), new Date(), post.getTags(), post.getFile());
        return "redirect:/home";
    }

    @GetMapping("/post/{postId}")
    String getPostPage(Authentication authentication, Model model, @PathVariable long postId){
        model.addAttribute("isLogin", (authentication != null));
        model.addAttribute("currentPage", "post_page");
        PostDtoResponse post = new PostDtoResponse(postService.getByIdWithComments(postId), authentication != null ? (User)authentication.getPrincipal() : null);
        Collection<CommentDtoResponse> comments = new ArrayList<>();
        for (Comment comment:post.getComments()){
            comments.add(new CommentDtoResponse(comment, authentication != null ? (User)authentication.getPrincipal() : null));
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "post_page";
    }

    @PostMapping("/post/add_comment/{postId}")
    String addComment(Model model, @PathVariable long postId, Authentication authentication, String comment){
        Post post = postService.getById(postId);
        User user = (User) authentication.getPrincipal();
        commentService.addComment(post, user, comment, new Date());
        return "redirect:/post/" + postId;
    }

    @GetMapping("/search_page")
    String getTagPage(Authentication authentication, Model model, @RequestParam(required = false) String tagNames,@RequestParam(required = false) String requestString){
        model.addAttribute("isLogin", (authentication != null));
        model.addAttribute("currentPage", "search_page");
        if (tagNames == null)
            tagNames = "";
        model.addAttribute("isLogin", (authentication != null));

        ArrayList<PostDtoResponse> posts = new ArrayList<>();
        User user;
        if (authentication!=null)
            user = (User) authentication.getPrincipal();
        else user = null;

        for(Post post :  postService.getPostsByParam(Arrays.asList(tagNames.split(",")), requestString)){
            posts.add(new PostDtoResponse(post,user));
        }
        model.addAttribute("posts", posts);
        return "search_page";
    }

    @PostMapping("/post/rate/{postId}")
    @ResponseBody
    void addRatePost(Model model, @PathVariable long postId, Authentication authentication){
        Long userId = ((User) authentication.getPrincipal()).getId();
        postService.ratePost(postId, userId);
    }

    @PostMapping("/comment/rate/{commentId}")
    @ResponseBody
    void addRateComment(@PathVariable long commentId, Authentication authentication){
        Long userId = ((User) authentication.getPrincipal()).getId();
        commentService.rateComment(userId, commentId);
    }

    @PostMapping("/upload")
    String testLoadImagePost(@RequestParam(value = "file", required = false) MultipartFile file){
        postImageService.storeFile(file);
        return "redirect:/test_load_img";
    }

    @GetMapping("/myImg/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        PostImage postImage = postImageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(postImage.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + postImage.getFileName() + "\"")
                .body(new ByteArrayResource(postImage.getData()));
    }
    @Autowired
    public PostController(PostService postService, CommentService commentService, PostImageService postImageService) {
        this.postService = postService;
        this.commentService = commentService;
        this.postImageService = postImageService;
    }
}
