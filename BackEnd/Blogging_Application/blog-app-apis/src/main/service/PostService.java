package com.blog.service;

import java.util.List;

import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;

public interface PostService {
	
	//Create Post
	public PostDTO createPost(PostDTO postDto,Integer cateId, Integer userId);
	
	//Update Post	
	public PostDTO updatePost(PostDTO postDto, Integer postId);
	
	//Get All Post
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	//Get Post By Id
	public PostDTO getPostById(Integer PostId);
	
	//Get All Post By User
	public PostResponse getPostByuser(Integer userId,Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	//Get All Post By Category
	public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize,String sortBy,String sortDirv);
	
	//Search Post
	public List<PostDTO> searchPost(String keyword);
	
	//Delete Post
	public void  deletePost(Integer postId);
	
	

}
