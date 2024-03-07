package com.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.CommentDAO;
import com.blog.dao.PostDAO;
import com.blog.dao.UserDao;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDTO;

@Service
public class CommentServiceImp implements CommentService{

	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CommentDAO commentDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	//create comment
	@Override
	public CommentDTO createComment(CommentDTO commentDto, Integer postId,Integer userId) {
		Post post = postDao.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Comment comment = mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = commentDao.save(comment);
		return mapper.map(savedComment, CommentDTO.class);
	}

	
	// delete comment
	@Override
	public void deleteComment(Integer commentId) {
		Comment com = commentDao.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","CommentID",commentId));
		commentDao.delete(com);
	}

}