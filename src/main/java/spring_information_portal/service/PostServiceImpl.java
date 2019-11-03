package spring_information_portal.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring_information_portal.entity.Post;
import spring_information_portal.entity.PostImage;
import spring_information_portal.entity.Tag;
import spring_information_portal.entity.User;
import spring_information_portal.repos.PostRepos;
import spring_information_portal.repos.TagRepos;
import spring_information_portal.repos.UserRepos;

import java.util.*;
import java.util.function.Predicate;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepos postRepos;

    @Autowired
    TagRepos tagRepos;

    @Autowired
    UserRepos userRepos;

    @Autowired
    CommentService commentService;

    @Autowired
    PostImageService postImageService;

    @Override
    @Transactional
    public Post getById(Long id) {
        Post post = postRepos.getById(id);
        return post;
    }

    @Override
    @Transactional
    public Post getByIdWithComments(Long id) {
        Post post = postRepos.getById(id);
        Hibernate.initialize(post.getComments());
        post.getComments().forEach(n->{
            Hibernate.initialize(n.getUserRate());
        });
        Hibernate.initialize(post.getUserRate());

        return post;
    }

    @Override
    public Collection<Post> getByUser(User user) {
        return postRepos.getByAuthorId(user.getId());
    }

    @Override
    @Transactional
    public Collection<Post> getAll() {
        Collection<Post> posts = postRepos.findOffsetLimit(0,50);
        posts.forEach(n->{
            Hibernate.initialize(n.getUserRate());
        });
        return posts;
    }

    @Override
    @Transactional
    public Collection<Post> getByTagId(long tagId) {
        List<Post> posts = tagRepos.getById(tagId).getPosts();
        for (Post post : posts){
            post.getAuthor();
        }
        return posts;
    }

    @Override
    public void createAndSave(Long authorId, String name, String text, Date date, String tags, MultipartFile file) {
        List<String> tagNames = new ArrayList<>(Arrays.asList(tags.split(",")));
        Collection<Tag> existTags = tagRepos.getByNameIn(tagNames);
        PostImage postImage = null;
        if (!file.isEmpty())
            postImage = postImageService.storeFile(file);
        tagNames.removeIf(new Predicate<String>() {
            private Collection<Tag> tags;
            @Override
            public boolean test(String s) {
                boolean contain = false;
                for (Tag tag : tags){
                    if (tag.getName().equals(s)) {
                        contain = true;
                        break;
                    }
                }
                return contain;
            }

            private Predicate<String> init(Collection<Tag> existTags){
                this.tags = existTags;
                return this;
            }
        }.init(existTags));

        if (tagNames.size()> 0){
            List<Tag> newTags = new ArrayList<>();
            tagRepos.saveAll(Tag.createTags(tagNames)).forEach(newTags::add);
            existTags.addAll(newTags);
        }
        Post post = new Post(name, new User(authorId), date, existTags, text, postImage != null ? postImage.getId() : -1);

        postRepos.save(post);
    }

    @Override
    public void save(Post post) {
        postRepos.save(post);
    }

    @Override
    @Transactional
    public Collection<Post> getPostsByParam(List<String> tagNames, String requestString) {
        Collection<Post> posts;
        if (tagNames.size() == 1 && tagNames.get(0).equals("")){
            if (requestString.equals("")){
                posts = postRepos.findOffsetLimit(0,50);
            }else{
                posts = postRepos.findByNameIgnoreCaseContainingOrderByDateDesc(requestString);
            }
        }else{
            if (requestString.equals("")){
                posts = postRepos.findByTags_NameInOrderByDateDesc(tagNames);
            }else{
                posts = postRepos.findByNameIgnoreCaseContainingAndTags_NameInOrderByDateDesc(requestString, tagNames);
            }

        }
        posts.forEach(n->{
            Hibernate.initialize(n.getUserRate());
        });
        return posts;
    }

    @Override
    @Transactional
    public void ratePost(Long postId, Long userId) {
        Post post = postRepos.getById(postId);
        User user = userRepos.getById(userId);

        if (post.getUserRate().contains(user)) {
            post.getUserRate().remove(user);
            user.getLikedPosts().remove(post);
            post.setRate(post.getRate() - 1);

            return;
        }
        post.setRate(post.getRate() + 1);
        post.getUserRate().add(user);
        user.getLikedPosts().add(post);
        postRepos.save(post);

    }


}
