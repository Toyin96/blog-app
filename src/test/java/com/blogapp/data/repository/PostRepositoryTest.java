package com.blogapp.data.repository;

import com.blogapp.data.models.Author;
import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.xml.datatype.DatatypeConstants;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*
Our application context(components) are only available at runtime.
during our test context, we need access to the containers hence that's
why we'll use @SpringbootTest which allows us to have access to the components
 */

@SpringBootTest
@Slf4j
@Rollback(value = false)
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

        log.info("Created a blog PostService --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

    }

    @Test
    void throwExceptionWhenSavingPostWithDuplicateTitle() {
        Post blogPost = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorum Ipsum is simply a dummy text");

        log.info("Created a blog PostService --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

        Post blogPost2 = new Post();
        blogPost2.setTitle("What is Fintech?");
        blogPost2.setContent("Lorum Ipsum is simply a dummy text");
        log.info("Created a blog PostService -->{}", blogPost2);
        assertThrows(DataIntegrityViolationException.class, ()-> postRepository.save(blogPost2));
    }

    @Test
    void whenPostIsSaved_thenSaveAuthor(){

        Post blogPost = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorum Ipsum is simply a dummy text");
        log.info("Created a blog PostService --> {}", blogPost);

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

    @Test
    @Transactional //keeps the transaction open for as many operations that we wanna perform. by default, it rolls back to previous state before transaction.
    @Rollback(value = false) // now we are saying that the database should not roll back after the transaction has been performed
    void deletePostByIdTest(){
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        log.info("Post fetched from the database -->{}", savedPost);
        //delete PostService
        postRepository.deleteById(savedPost.getId());

        savedPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertThat(savedPost).isNull();
        log.info("Post fetched from the database -->{}", savedPost);
    }

    @Test
    void updateSavedPostTest(){
        Post oldPost = postRepository.findById(41).orElse(null);
        assertThat(oldPost).isNotNull();

        oldPost.setTitle("This is Toyin's new title");
        postRepository.save(oldPost);

        Post updatedPost = postRepository.findById(oldPost.getId()).orElse(null);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo("This is Toyin's new title");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updatePostAuthorTest(){
        Post oldPost = postRepository.findById(41).orElse(null);
        assertThat(oldPost.getAuthor()).isNull();

        Author author = new Author();
        author.setFirstName("Mr Kunle");
        author.setLastName("Seun");
        author.setProfession("SOftware engineer");
        author.setPhoneNumber("09043910175");
        author.setEmail("Seun@gmail.com");

        oldPost.setAuthor(author);
        postRepository.save(oldPost);

        assertThat(oldPost.getAuthor()).isNotNull();
        assertThat(oldPost.getAuthor().getFirstName()).isEqualTo("Mr Kunle");
        assertThat(oldPost.getAuthor()).isEqualTo(author);

    }

    @Test
    void addCommentToAnAlreadyExistingPostTest(){
        //fetch thr ost from the db
        Post oldPost = postRepository.findById(41).orElse(null);
        assertThat(oldPost).isNotNull();
        assertThat(oldPost.getComments()).hasSize(0);

        //create a comment
        Comment comment1 = new Comment("Billy Goat","Really insightful article");
        Comment comment2 = new Comment("Peter Bread", "Really insightful article");

        //map the PostService and comment
        oldPost.addComment(comment1, comment2);

        //save the PostService
        postRepository.save(oldPost);

        assertThat(oldPost.getComments()).hasSize(2);
    }
}