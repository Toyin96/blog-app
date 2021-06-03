package com.blogapp.service.cloud;

import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exception.PostObjectNullException;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
@SpringBootTest
class CloudinaryCloudStorageImplTest {


    @Autowired @Qualifier("cloudinary")
    CloudStorageService cloudStorageServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void uploadImageFile(){
        File file = new File("C:\\Users\\ADMIN\\Downloads\\blogapp\\blogapp\\src\\main\\resources\\static\\asset\\images\\author-image1.jpg");

        assertThat(file.exists()).isTrue();
        Map<Object, Object> params = new HashMap<>();
        params.put("folder", "blogapp/");
        params.put("overwrite",true);


        try{
            cloudStorageServiceImpl.uploadImage(file, params);
        }catch(IOException e){
            log.info("Error occurred --> {}", e.getMessage());
        }
    }

    @Test
    void upoadMultipartImageTest() throws IOException {
        PostDto postDto = new PostDto();
        postDto.setTitle("Test");
        postDto.setContent("test");
        Path path = Paths.get("C:\\Users\\ADMIN\\Downloads\\blogapp\\blogapp\\src\\main\\resources\\static\\asset\\images\\author-image1.jpg");
        assertThat(path.toFile()).exists();

        MultipartFile multipartFile = new MockMultipartFile("images.jpeg", "images.jpeg", "img/jpeg", Files.readAllBytes(path));

        log.info("multipart object created --> {}", multipartFile);
        assertThat(multipartFile).isNotNull();
        postDto.setImageFile(multipartFile);

        assertThat(multipartFile.isEmpty()).isFalse();
        log.info("file name --> {}", postDto.getImageFile().getOriginalFilename());
        cloudStorageServiceImpl.uploadImage(multipartFile, ObjectUtils.asMap("public_id", "blogapp/" + extractFileName(Objects.requireNonNull(postDto.getImageFile().getOriginalFilename()))
       ));
        assertThat(postDto.getImageFile().getOriginalFilename()).isEqualTo("images.jpeg");
    }

    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }

}