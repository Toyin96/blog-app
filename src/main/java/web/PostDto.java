package web;

import org.springframework.web.multipart.MultipartFile;

public class PostDto {

    private String title;

    private String content;

    private MultipartFile coverImageUrl;
}
