package com.example.demo.Demo.App.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Demo.App.model.BlogModel;
import com.example.demo.Demo.App.request_response.BasicResponse;
import com.example.demo.Demo.App.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class BlogController {

    public BlogService blogService;
    ObjectMapper objectMapper;
    
    public BlogController(BlogService blogService,ObjectMapper mapper) {
        this.blogService = blogService;
        this.objectMapper = mapper;
    }

    @PostMapping("/addBlog")
    public ResponseEntity<BasicResponse> addBlog(
        @RequestParam("blog_image") MultipartFile blogImage,
        @RequestParam("blog_data") String blogData
    ){
        BasicResponse basicResponse = new BasicResponse();
        try {
            BlogModel blogModel = null;
            blogModel = objectMapper.readValue(blogData, BlogModel.class);
            basicResponse = blogService.addBlog(blogModel,blogImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(basicResponse,HttpStatus.OK);
    }

    @GetMapping("/allBlogs")
    public ResponseEntity<BasicResponse> getAllBlogs(){
        BasicResponse basicResponse = new BasicResponse();
        try {
            basicResponse = blogService.getAllBlogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(basicResponse,HttpStatus.OK);
    }

    @GetMapping("/allBlogs/{id}")
    public ResponseEntity<BasicResponse> getBlogById(@PathVariable int id){
        BasicResponse basicResponse = new BasicResponse();
        try {
            basicResponse = blogService.getBlogById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(basicResponse,HttpStatus.OK);
    }

    @PostMapping("/updateBlog")
    public ResponseEntity<BasicResponse> updateBlog(
        @RequestParam("blog_image") MultipartFile blogImage,
        @RequestParam("blog_data") String blogData
    ){
        BasicResponse basicResponse = new BasicResponse();
        try {
            BlogModel blogModel = null;
            blogModel = objectMapper.readValue(blogData, BlogModel.class);
            basicResponse = blogService.updateBlog(blogModel,blogImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(basicResponse,HttpStatus.OK);
    }


    @GetMapping("/deleteBlogById/{id}")
    public ResponseEntity<BasicResponse> deleteBlogById(@PathVariable int id){
        BasicResponse basicResponse = new BasicResponse();
        try {
            basicResponse = blogService.deleteBlogById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(basicResponse,HttpStatus.OK);
    }

}
