package com.blog.service;

import java.util.List;

import com.blog.payloads.CategoryDto;


//interface concept was used for loose coupling so can change implementation at anytime
public interface CategoryService {
	
	//create 
	public CategoryDto createCategory(CategoryDto cateDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto cateDto, Integer cateId);
	
	//get
	public CategoryDto getCategory(Integer cateId);
	
	//getAll
	public List<CategoryDto> getAllCategories();
	
	//delete
	public void deleteCategory(Integer cateId);

}
