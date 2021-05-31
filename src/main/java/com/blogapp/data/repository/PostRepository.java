package com.blogapp.data.repository;

import com.blogapp.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*Our repo has to be an interface that extends jpa repository. Note, we have to
pass the entity and the id type. this say hibernate can generate sql
scripts for whatever CRUD operation we wanna perform
 */

@Repository // mark it as a component
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findByTitle(String title);

    @Query("select p from Post as p  where p.title = ?1")
    Post FindByPostTitle(@Param("tile")String title);

}
