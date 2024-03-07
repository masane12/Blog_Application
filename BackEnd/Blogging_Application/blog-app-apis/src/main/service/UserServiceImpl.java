package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.UserDao;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	//here UserDao is interface but we are doing autowiring here without 
	//UserDao having implementation class 
	
	//@Autowired to inject dependencies, including interfaces, into other Spring-managed components, 
	//such as services. This feature allows for a high level of abstraction and flexibility in your
	//code, and it's a key aspect of the framework's Inversion of Control (IoC) and Dependency Injection (DI) principles.
	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userdto) {
		
		//we need only User(Entity) object to save in database
		//so convert UserDTO object(userdto) to User object(user)
		//with the help of method dtoToUser
		User user = dtoToUser(userdto);
		
		// save the newly created user in database
		User savedUser = userDao.save(user);
		
		// then again changed User to DTO as return value is UserDTO
		return userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer userId) {
		
		// 1st we have to find which user we want to update by userId
		User user = userDao.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		//if user found then change Name, Email and About of User
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		
		// then save that user in database with help of DAO layer method 
		User updatedUser = userDao.save(user);
		
		//but we have to return DTO here so convert User TO DTO
		UserDTO userdto1 = userToDto(updatedUser);
		
		// return DTO
		return userdto1;
	}

	
	@Override
	public UserDTO getUserById(Integer userId) {
		User user = userDao.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		return userToDto(user);
		 
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> allUsers = userDao.findAll();
		List<UserDTO> userDtos = allUsers.stream().map(user -> userToDto(user)).collect(Collectors.toList());	
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userDao.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		userDao.delete(user);

	}

	
//	public User dtoToUser(UserDTO userDto)
//	{
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		return user;
//	}

// by using ModelMapper
	public User dtoToUser(UserDTO userDto)
	{
		User user = modelMapper.map(userDto, User.class);
		return user;
	}
	
	
//	public UserDTO userToDto(User user)
//	{
//		UserDTO userDto = new UserDTO();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		return userDto;
//	}
	
	public UserDTO userToDto(User user)
	{
		                              // user converted into UserDTO
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}
}
