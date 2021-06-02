package com.blogapp.service.cloud;

import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exception.PostObjectNullException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
}