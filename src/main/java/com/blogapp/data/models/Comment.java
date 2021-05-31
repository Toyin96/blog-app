package com.blogapp.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String commentatorName;

    @Column(nullable = false, length = 150)
    private String content;

    @CreationTimestamp
    private LocalDateTime dareCreated;

    public Comment(String commentatorName, String content){
        this.content = content;
        this.commentatorName = commentatorName;
    }

}
