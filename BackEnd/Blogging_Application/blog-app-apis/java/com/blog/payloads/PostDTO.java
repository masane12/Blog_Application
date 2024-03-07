package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	
	private Integer postId;
	
	@NotBlank
	@Size(min=4, message = "Title should be of min 4 chars")
	private String title;
	
	@NotBlank
	@Size(min=10, message = "Content should be of min 10 chars")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	// if we write private Category category then it go in recursion 
	// as Post is present in Category that's we take CategoryDto as Post is not present in CategoryDto
	private CategoryDto category;
	
	// if we write private User user then it go in recursion 
	// as Post is present in User that's we take UserDTO as Post is not present in UserDTO	
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();
	
	
	

}