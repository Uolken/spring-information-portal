package spring_information_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_information_portal.entity.Comment;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.User;
import spring_information_portal.repos.CommentRepos;
import spring_information_portal.repos.PostRepos;
import spring_information_portal.repos.UserRepos;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepos commentRepos;

    @Autowired
    UserRepos userRepos;

    @Autowired
    PostRepos postRepos;

    @Override
    public void addComment(Post post, User author, String text, Date date) {
        Comment comment = new Comment(post, author,text, date, 0);
        commentRepos.save(comment);

    }

    @Override
    @Transactional
    public void rateComment(Long userId, Long commentId) {
        User user = userRepos.getById(userId);
        Comment comment = commentRepos.getById(commentId);
        if (comment.getUserRate().contains(user)){
            comment.getUserRate().remove(user);
            comment.setRate(comment.getRate()-1);
            user.getLikedComm().remove(comment);
            commentRepos.save(comment);
            return;

        }
        comment.getUserRate().add(user);
        comment.setRate(comment.getRate()+1);
        user.getLikedComm().add(comment);
        commentRepos.save(comment);
    }
}
