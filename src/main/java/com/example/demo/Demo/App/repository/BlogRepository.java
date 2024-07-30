package com.example.demo.Demo.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Demo.App.model.BlogModel;

public interface BlogRepository extends JpaRepository<BlogModel,Integer> {
    
}
