package com.blogapp.service;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.service.cloud.CloudStorageService;
import com.blogapp.web.exception.PostObjectNullException;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.blogapp.web.dto.PostDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    CloudStorageService cloudStorageService;

    @Autowired
    PostRepository postRepository;

    @Override
    public Post savePost(PostDto postDto) throws PostObjectNullException {

        if (postDto == null) {
            throw new PostObjectNullException("Post cannot be null");
        }

        Post post = new Post();

        if (postDto.getImageFile() != null && !postDto.getImageFile().isEmpty()) {
//            Map<Object, Object> params = new HashMap<>();
//            params.put("public_id", "blogapp/" + postDto.getImageFile().getName());
//            params.put("overwrite", true);
//            log.info("params -->{}", params);

            try {
                Map<?, ?> uploadResult = cloudStorageService.uploadImage(postDto.getImageFile(), ObjectUtils.asMap("public_id",
                        "blogapp/" + postDto.getImageFile().getName(), "overwrite", true));

                post.setCoverImageUrl(String.valueOf(uploadResult.get("url")));
                log.info("Image url --> {}", uploadResult.get("url"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        log.info("Post object before saving -->{}", post);
        try {
            return postRepository.save(post);
        }catch (DataIntegrityViolationException ex){
            log.info("Exception occurred -->{}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public Post findPostById(Integer id) {
        return null;
    }

    @Override
    public void deletePostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer id, Comment comment) {
        return null;
    }
}
