package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// DTO class is used to access data fields of User class 
// if we don't want all fields of User Entity class then we can create DTO of user 
// by this we are not directly exposing User Entity to APIs
// Entity is to only connect with Database then
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	private int id;
	
	@NotEmpty
	@Size(min = 2, message = "Username must be min 4 charchters")
	private String name;
	
	@Email(message  = "Email  address is not valid!!")
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be of min pf 3 chars and max of 10 chars" )
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
	private String  password;
	
	@NotNull
	private String about;
	 
}
