package com.blogapp.web.controllers;

import com.blogapp.data.models.Post;
import com.blogapp.service.PostService;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exception.PostObjectNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postServiceImpl;

    @GetMapping()
    public String getIndex(Model model){
        List<Post> postList = postServiceImpl.findAllPosts();
        model.addAttribute("postList", postList);

        return "index"; //Spring boot will look for an html page that matches this name in template folder
    }


    @GetMapping("/create")
    public String getPostForm(Model model){
        /*
        We're passing a model to this page due to the http method which is a getmapping
         */
//        model.addAttribute("postdto", new PostDto());
        model.addAttribute("error", false);
        /*
        Now, we're creating and passing a post dto to the model so that it returns the postdto
         */
        return "create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute("postdto") @Valid PostDto postDto, BindingResult result, Model model){
        /*the @valid is to enforce our valid constraint
            the @ModelAttribute is used during MVC
         */
        log.info("Post dto received -->{}", postDto);

        if (result.hasErrors()){

        }

        try{
            postServiceImpl.savePost(postDto);
        }catch (PostObjectNullException pe){
            log.info("Exception occurred -->{}", pe.getMessage());
        }catch(DataIntegrityViolationException dx){
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Title Not Accepted, Already Exists");
//            model.addAttribute("post", postDto);
            return "create";
        }

        return "redirect:/posts";
    }

    @ModelAttribute
    public void createPostModel(Model model){
        model.addAttribute("postdto", new PostDto());
    }


}
