package spring_information_portal.service;

import spring_information_portal.entity.Post;
import spring_information_portal.entity.User;

import java.util.Date;

public interface CommentService {
    public void addComment(Post post, User author, String text, Date date);
    public void rateComment(Long userId, Long commentId);
}
