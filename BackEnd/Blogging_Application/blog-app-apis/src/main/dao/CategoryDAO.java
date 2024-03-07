package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
