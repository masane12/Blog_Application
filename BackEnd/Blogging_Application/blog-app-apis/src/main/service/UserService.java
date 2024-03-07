package com.blog.service;

import java.util.List;


import com.blog.payloads.UserDTO;

public interface UserService {
	
	// we are not giving entity directly to service
	// DTO are used to transfer data 
	// DTO - data transfer object
	
	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user, Integer userId);
	
	UserDTO getUserById(Integer userId);
	
	List<UserDTO> getAllUsers();
	
	void deleteUser(Integer userId);

}
