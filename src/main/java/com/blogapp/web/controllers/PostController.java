package com.blogapp.web.controllers;

import com.blogapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class PostController {

    @Autowired
    PostService postServiceImpl;

    @GetMapping("/posts")
    public String getIndex(){
        return "index"; //Spring boot will look for an html page that matches this name in template folder
    }

    @PostMapping("/post")
    public String savePost(){
        return null;
    }

    @GetMapping("/post/create")
    public String getPostForm(){
        return "create";
    }

}
