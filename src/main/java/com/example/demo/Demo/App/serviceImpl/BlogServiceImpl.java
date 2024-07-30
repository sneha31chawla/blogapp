package com.example.demo.Demo.App.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Demo.App.model.BlogModel;
import com.example.demo.Demo.App.repository.BlogRepository;
import com.example.demo.Demo.App.request_response.BasicResponse;
import com.example.demo.Demo.App.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    public BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BasicResponse addBlog(BlogModel blogModel, MultipartFile blogImage) {
        BasicResponse basicResponse = new BasicResponse();
        try {

            if (blogModel != null && !blogImage.isEmpty()) {

                // file upload start
                File savFile = new ClassPathResource("static/blogImages").getFile();
                Path path = Paths.get(savFile.getAbsolutePath() + File.separator + blogImage.getOriginalFilename());
                Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                // file upload end

                BlogModel blogModel2 = new BlogModel();
                blogModel2.setBlogTitle(blogModel.getBlogTitle());
                blogModel2.setBlogDescription(blogModel.getBlogDescription());
                blogModel2.setBlogImage(blogImage.getOriginalFilename());

                BlogModel b = blogRepository.save(blogModel2);

                basicResponse.setData(b);
                basicResponse.setMessage("Blog added successfully");
                basicResponse.setStatus(true);

            } else {
                basicResponse.setMessage("Blog addtion failed!");
                basicResponse.setStatus(false);
            }
        } catch (Exception e) {
            basicResponse.setMessage(e.getMessage());
            basicResponse.setStatus(false);
        }
        return basicResponse;
    }

    @Override
    public BasicResponse getAllBlogs() {
        BasicResponse basicResponse = new BasicResponse();
        try {
            List<BlogModel> allBlogs = blogRepository.findAll();
            if (!allBlogs.isEmpty()) {
                basicResponse.setData(allBlogs);
                basicResponse.setMessage("All Blogs");
                basicResponse.setStatus(true);
            } else {
                basicResponse.setMessage("No Blogs");
                basicResponse.setStatus(false);
            }
        } catch (Exception e) {
            basicResponse.setMessage(e.getMessage());
            basicResponse.setStatus(false);
        }
        return basicResponse;
    }

    @Override
    public BasicResponse getBlogById(int id) {
        BasicResponse basicResponse = new BasicResponse();
        try {

            Optional<BlogModel> myBlog = blogRepository.findById(id);
            if (myBlog.isPresent()) {
                BlogModel blogModel = myBlog.get();
                basicResponse.setData(blogModel);
                basicResponse.setMessage("Blog details");
                basicResponse.setStatus(true);
            } else {
                basicResponse.setMessage("No blog details");
                basicResponse.setStatus(false);
            }
        } catch (Exception e) {
            basicResponse.setMessage(e.getMessage());
            basicResponse.setStatus(false);
        }
        return basicResponse;
    }

    @Override
    public BasicResponse updateBlog(BlogModel blogModel, MultipartFile blogImage) {
        BasicResponse basicResponse = new BasicResponse();
        try {
            Optional<BlogModel> checkBlog = blogRepository.findById(blogModel.getBlogId());
            if (checkBlog.isPresent()) {
                BlogModel b = checkBlog.get();

                // file upload start
                File savFile = new ClassPathResource("static/blogImages").getFile();
                Path path = Paths.get(savFile.getAbsolutePath() + File.separator + blogImage.getOriginalFilename());
                Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                // file upload end

                b.setBlogTitle(blogModel.getBlogTitle());
                b.setBlogDescription(blogModel.getBlogDescription());
                b.setBlogImage(blogImage.getOriginalFilename());

                BlogModel blogModel2 = blogRepository.save(b);

                basicResponse.setData(blogModel2);
                basicResponse.setMessage("Blog updated successfully");
                basicResponse.setStatus(true);

            } else {
                basicResponse.setMessage("Blog updation failed!");
                basicResponse.setStatus(false);
            }
        } catch (Exception e) {
            basicResponse.setMessage(e.getMessage());
            basicResponse.setStatus(false);
        }
        return basicResponse;
    }

    @Override
    public BasicResponse deleteBlogById(int id) {
        BasicResponse basicResponse = new BasicResponse();
        try {
            Optional<BlogModel> checkBlog = blogRepository.findById(id);
            if (checkBlog.isPresent()) {
                BlogModel blogModel = checkBlog.get();
                blogRepository.delete(blogModel);
                basicResponse.setData(blogModel);
                basicResponse.setMessage("Blog deleted successfully");
                basicResponse.setStatus(true);
            } else {
                basicResponse.setMessage("Blog deletion failed");
                basicResponse.setStatus(false);
            }
        } catch (Exception e) {
            basicResponse.setMessage(e.getMessage());
            basicResponse.setStatus(false);
        }
        return basicResponse;
    }

}
