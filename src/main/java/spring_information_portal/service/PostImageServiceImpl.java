package spring_information_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import spring_information_portal.entity.PostImage;
import spring_information_portal.repos.PostImageRepos;

import java.io.IOException;

@Service
public class PostImageServiceImpl implements PostImageService{
    @Autowired
    PostImageRepos postImageRepos;

    @Override
    public PostImage storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            PostImage postImage = new PostImage(fileName, file.getContentType(), file.getBytes());

            return postImageRepos.save(postImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public PostImage getFile(Long fileId) {

        return postImageRepos.findById(fileId).get();
    }
}
