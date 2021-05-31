package com.blogapp.web.controllers;

import com.blogapp.service.PostService;
import com.blogapp.web.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postServiceImpl;

    @GetMapping()
    public String getIndex(){
        return "index"; //Spring boot will look for an html page that matches this name in template folder
    }


    @GetMapping("/create")
    public String getPostForm(Model model){
        /*
        We're passing a model to this page due to the http method which is a getmapping
         */
        model.addAttribute("post", new PostDto());
        /*
        Now, we're creating and passing a post dto to the model so that it returns the postdto
         */
        return "create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute @Valid PostDto postDto){
        /*the @valid is to enforce our valid constraint
            the @ModelAttribute is used during MVC
         */
        log.info("Post dto received -->{}", postDto);
        return "index";
    }


}
