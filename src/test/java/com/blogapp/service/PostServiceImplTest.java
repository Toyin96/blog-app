package com.blogapp.service;

import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import web.PostDto;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    @Mock // we're injecting a mock of the postrepository into the postrepositorytest
    PostRepository postRepository;

    @InjectMocks
    PostService postServiceImpl = new PostServiceImpl();
    Post testPost;

    @BeforeEach
    void setUp() {
        testPost = new Post();
        MockitoAnnotations.openMocks(this); //
    }

    @Test
    void whenTheSaveMethodIsCalled_thenRepositoryIsCalledOnceTest(){
        when(postServiceImpl.savePost(new PostDto())).thenReturn(testPost);
        postServiceImpl.savePost(new PostDto());

        verify(postRepository, times(1)).save(testPost);
    }
}