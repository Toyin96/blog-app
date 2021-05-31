package com.blogapp.service.util;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;

@Data
@Component
public class CloudinaryConfig {

    @Value("${CLOUD_NAME}")
    private String cloudName;
    @Value("${API_KEY}")
    private String apiKey;
    @Value("${API_SECRET}")
    private String apiSecret;

}
