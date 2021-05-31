package com.blogapp.web.dto;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDto {

    @NotNull(message = "Title cannot be null") // in order to use this constraint, we included Spring Boot Starter Validation dependency from mvn repository
    private String title;

    @NotNull(message = "Post content cannot be null")
    private String content;

    private MultipartFile coverImageUrl;
}
