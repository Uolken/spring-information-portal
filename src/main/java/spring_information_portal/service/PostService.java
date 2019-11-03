package spring_information_portal.service;

import org.springframework.web.multipart.MultipartFile;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface PostService {
    public Post getById(Long id);
    public Post getByIdWithComments(Long id);
    public Collection<Post> getByUser(User user);
    public Collection<Post> getAll();
    public Collection<Post> getByTagId(long tagId);
    public void createAndSave(Long authorId, String name, String text, Date date, String tags, MultipartFile file);
    public void save(Post post);
    public Collection<Post> getPostsByParam(List<String> tagNames, String requestString);
    public void ratePost(Long postId, Long userId);
}
