package com.blogapp.service;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exception.PostDoesNotExistException;
import com.blogapp.web.exception.PostObjectNullException;

import java.util.List;

public interface PostService {

    Post savePost(PostDto postDto) throws PostObjectNullException;

    List<Post> findAllPosts();

    Post updatePost(PostDto postDto);

    Post findPostById(Integer id) throws PostDoesNotExistException;

    void deletePostById(Integer id);

    Post addCommentToPost(Integer id, Comment comment);

    List<Post> findPostsInDescOrder();
}
