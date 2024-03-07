package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService cateService;
	
	//PostMapping(create)
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto cateDto)
	{
	     CategoryDto createCategory = cateService.createCategory(cateDto);
	     return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
	}
	
	//PutMapping(update)
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto cateDto, @PathVariable Integer id)
	{
		CategoryDto updatedCategory = cateService.updateCategory(cateDto, id);
		return ResponseEntity.ok(updatedCategory);
	}
	
	//DeleteMapper(delete)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id)
	{
		cateService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true), HttpStatus.OK);
	}
	
	//GetMapping(getAllCategories)
	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> categories = cateService.getAllCategories();
		return  ResponseEntity.ok(categories);		
	}
	
	
	//GetMapping(getCategory)
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id)
	{		
		return ResponseEntity.ok(cateService.getCategory(id));
	}
	
	
	
	
	

}