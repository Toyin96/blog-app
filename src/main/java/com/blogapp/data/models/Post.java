package com.blogapp.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="blog_post")
@Data // for lombok to to generate your getters and setters.
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // we set the id ourselves
    private Integer id; // any class that has the entity annotation  must have an id.

    @Column(nullable = false, length = 50, unique = true) // nullable set to true means it can't be null
    private String title;

    @Column(length = 400) // the default length is 250
    private String content;

    private String coverImageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST) //the first is for the class that you're currently in. 1 author to many blog posts
    @JoinColumn()
    private Author author;

    @CreationTimestamp // database automatically stamps when the post was created
    private LocalDate dateCreated;

    @UpdateTimestamp // database automatically stamps when the post was updated
    private LocalDate dateModified;

    @OneToMany
    private List<Comment> comment; //whenever the relationship you're extending to, it has to be in a list
}
