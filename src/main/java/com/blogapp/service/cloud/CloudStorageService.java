package com.blogapp.service.cloud;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CloudStorageService {
    Map<Object, Object> uploadImage(File file, Map<Object, Object> imageProperties) throws IOException;
};
