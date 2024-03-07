package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ApiResponse;

//declared class for Exception Handling and method in this class we can to handle 
//exceptions in Controller

// do global exception handling of all controllers
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// here we are handling ResourceNotFoundException(custom exception) 
	// if we are throwing this exception from controller then resourceNotFoundExceptionHandler() will run 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
	
	
	//not understand
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			 String fieldName = ((FieldError)error).getField();
			 String message = error.getDefaultMessage(); 
			 resp.put(fieldName, message);});	
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	

}
