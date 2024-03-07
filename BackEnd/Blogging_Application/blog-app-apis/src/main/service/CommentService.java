package com.blog.service;

import com.blog.payloads.CommentDTO;

public interface CommentService {
	
	//create comment
	public CommentDTO createComment(CommentDTO comment,Integer PostId, Integer userId);
	
	//delete comment
	public void deleteComment(Integer commentId);
	

}
