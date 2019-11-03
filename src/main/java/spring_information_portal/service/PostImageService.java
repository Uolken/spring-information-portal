package spring_information_portal.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring_information_portal.entity.PostImage;

public interface PostImageService {
    public PostImage storeFile(MultipartFile file);

    public PostImage getFile(Long fileId);

}
