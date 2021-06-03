package com.blogapp.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class PostDto {

    @NotEmpty(message = "Title cannot be null") // in order to use this constraint, we included Spring Boot Starter Validation dependency from mvn repository
    private String title;

    @NotEmpty(message = "Post content cannot be null")
    private String content;

    private MultipartFile imageFile;
}
