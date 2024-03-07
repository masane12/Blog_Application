package com.blog.entities;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "post_title", length = 100, nullable = false)
	private String title;
	
	@Column(name = "post_content", nullable = false)
	private String content;
	
	
	private String imageName;
	
	private Date addedDate;
		
	// if we don't know the user and Category then we can't add Post
	
	// Many Post by One User
	@ManyToOne
	private User user;
	
	// 1 Post --> many Comments
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	
	
	// Many Post in One Category
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	

}
