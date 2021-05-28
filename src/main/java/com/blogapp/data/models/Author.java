package com.blogapp.data.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //every entity have to have an id
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // automatically generate the id
    private Integer id;

    private String firstName;

    @Column(nullable = false)
    private String lastName;
    private String profession;

    @Column(unique = true)
    private String email;
    private String phoneNumber;

    @OneToMany
    @ToString.Exclude
    private List<Post> posts;

    public void addPost(Post post){
        if(posts == null){
            posts = new ArrayList<>();
        }
        posts.add(post);
    }
}
