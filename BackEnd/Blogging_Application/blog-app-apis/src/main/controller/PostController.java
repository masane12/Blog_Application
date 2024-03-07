package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstant;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.service.FileService;
import com.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postSer;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	// create Post with User and which Category
	@PostMapping("/user/{userId}/category/{cateId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto, @PathVariable Integer userId, @PathVariable Integer cateId)
	{
		PostDTO createPost = postSer.createPost(postDto, cateId, userId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}
	
	// Update Post by Id
	@PutMapping("/update/post/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable Integer postId)
	{
		PostDTO updatedPost = postSer.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPost);
		
	}
	
	// Get Post by Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required=false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required=false) String sortDir,
			@PathVariable Integer categoryId)
	{
		PostResponse allPost = postSer.getPostByCategory(categoryId, pageNumber, pageSize,sortBy,sortDir);
	    return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	
	// Get Post By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required=false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required=false) String sortDir,
			@PathVariable Integer userId)
	{
		PostResponse allPost = postSer.getPostByuser(userId, pageNumber, pageSize,sortBy,sortDir);
	    return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	
	
	// Get Post By Id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId)
	{
		return ResponseEntity.ok(postSer.getPostById(postId));
	}
	
	// Get All Post
//	@GetMapping("/posts")
//	public ResponseEntity<List<PostDTO>> getAllPost()
//	{
//		return ResponseEntity.ok(postSer.getAllPost());
//	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required=false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required=false) String sortDir) 
	{
		PostResponse allPost = postSer.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	
	// Delete Post
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		postSer.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true),HttpStatus.OK);
	}
	
	
	
	@GetMapping("posts/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keyword") String keyword)
	{
		List<PostDTO> result = postSer.searchPost(keyword);
		return new ResponseEntity<List<PostDTO>>(result,HttpStatus.OK);
	}

	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(
			@RequestParam("image")MultipartFile image,
			@PathVariable Integer postId) throws IOException
	{
		PostDTO postDto = postSer.getPostById(postId);
		String fileName = fileService.uploadImage(path, image);	
	    postDto.setImageName(fileName);
	    PostDTO updatePost = postSer.updatePost(postDto, postId);
	    return new ResponseEntity<PostDTO>(updatePost,HttpStatus.OK);
	}
	
	//method to serve files
	
		@GetMapping(value = "post/images/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response ) throws IOException
		{
			InputStream resource = fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());	
		}
}
