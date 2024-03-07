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
import com.blog.payloads.UserDTO;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	//POST - create user
	
	@PostMapping("/")
	// method name 'createUser' - accepts HTTP Post request UserDTO object as the request body(@RequestBody) 
	// and returns a ResponseEntity containing a UserDTO.
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto)
	{
		// createUser from a userservice object passing the received userdto as a parameter. 
		// this method call is responsible for creating a new user based on the provided UserDTO.
		UserDTO createUserDto = userservice.createUser(userdto);
		
		// creates a new ResponseEntity object. It encapsulates the 
		// createUserDto(newly created user) +  sets the HTTP status code as HttpStatus.CREATED 
		// (HTTP 201 Created)- the user creation was successful.
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	//PUT - update user
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userdto, @PathVariable Integer id)
	{
		UserDTO updatedUser = userservice.updateUser(userdto, id);
		return ResponseEntity.ok(updatedUser); //HttpStatus.OK (HTTP 200 OK)
	}
	
	//DELETE - delete user
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@Valid @PathVariable Integer id)
	{
		userservice.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	
	//GET - user get
	@GetMapping("listofusers/")
	public ResponseEntity<List<UserDTO>> getAllUsers()
	{
		return ResponseEntity.ok(userservice.getAllUsers());
	}
	
	@GetMapping("singleuser/{id}")
	public ResponseEntity<UserDTO> getSingleUser(@Valid @PathVariable Integer id)
	{
		return ResponseEntity.ok(userservice.getUserById(id));
	}

}