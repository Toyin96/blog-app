package com.blogapp.data.repository;

import com.blogapp.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Our repo has to be an interface that extends jparepository. Note, we have to
we pass the entity and the id type. this say hibernate can generate sql
scripts for whatever CRUD operation we wanna perform
 */

@Repository // mark it as a component
public interface PostRepository extends JpaRepository<Post, Integer> {


}
