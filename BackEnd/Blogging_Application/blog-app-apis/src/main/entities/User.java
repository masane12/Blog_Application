package com.blog.entities;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Id // id will be primary key of table
	@GeneratedValue(strategy = GenerationType.AUTO) // key will be auto-incremented
	private int id;
	
	@Column(name="name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "email", length = 50,unique=true, nullable = false)
	private String email;
	
	@Column(name="password", nullable = false, unique=true)
	private String password;
	
	@Column(name = "about")
	private String about;
	
	// fetch = fetchType.LAZY (they will be loaded from the database only when they are explicitly accessed or requested 
	    // by your application code. When you load a Parent object from the database, the associated Child entities will 
	    // not be loaded immediately. Accessing the children list will trigger a database query to fetch the related Child 
	    // entities at that point in your code.)
	// 1 User can write multiple post(1 User --> multiple Post)
	// if we fetch User then his all post come directly from database
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private  List<Post> userPost = new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	

}