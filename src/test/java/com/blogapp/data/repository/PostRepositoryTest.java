package com.blogapp.data.repository;

import com.blogapp.data.models.Author;
import com.blogapp.data.models.Post;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import javax.xml.datatype.DatatypeConstants;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
Our application context(components) are only available at runtime.
during our test context, we need access to the containers hence that's
why we'll use @SpringbootTest which allows us to have access to the components
 */

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"}) //we want this turn to also run each time we run our tests. By default, it runs before the tests
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository; // We need to inject the postrepository in order to perform the CRRUD operations.
    @BeforeEach
    void setUp() {


    }

    @Test
    void savePostToDbTest(){
        Post blogPost = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorum Ipsum is simply a dummy text");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

    }

    @Test
    void throwExceptionWhenSavingPostWithDuplicateTitle() {
        Post blogPost = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorum Ipsum is simply a dummy text");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

        Post blogPost2 = new Post();
        blogPost2.setTitle("What is Fintech?");
        blogPost2.setContent("Lorum Ipsum is simply a dummy text");
        log.info("Created a blog post -->{}", blogPost2);
        assertThrows(DataIntegrityViolationException.class, ()-> postRepository.save(blogPost2));
    }

    @Test
    void whenPostIsSaved_thenSaveAuthor(){

        Post blogPost = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorum Ipsum is simply a dummy text");
        log.info("Created a blog post --> {}", blogPost);

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Wick");
        author.setEmail("john@mail.com");
        author.setPhoneNumber("09052672738");

        //map relationships
        blogPost.setAuthor(author);
        author.addPost(blogPost);

        postRepository.save(blogPost);
        log.info("blogpost has been saved -->{}", blogPost);
    }

    @Test
    void findAllPostInTheDbTest(){
        List<Post> existingPosts = postRepository.findAll();
        assertThat(existingPosts).isNotNull();
        assertThat(existingPosts).hasSize(5);
    }


}