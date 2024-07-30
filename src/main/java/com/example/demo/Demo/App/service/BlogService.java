package com.example.demo.Demo.App.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Demo.App.model.BlogModel;
import com.example.demo.Demo.App.request_response.BasicResponse;

public interface BlogService {

    BasicResponse addBlog(BlogModel blogModel, MultipartFile blogImage);

    BasicResponse getAllBlogs();

    BasicResponse getBlogById(int id);

    BasicResponse updateBlog(BlogModel blogModel, MultipartFile blogImage);

    BasicResponse deleteBlogById(int id);
    
}
