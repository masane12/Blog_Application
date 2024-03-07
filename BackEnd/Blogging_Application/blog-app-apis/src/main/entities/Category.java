package com.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name = "title", length = 100)
	private String categoryTitle;
	
	@Column(name = "description")
	private String categorydescription;
	
	// 1 category can have multiple posts
	// 1 category ---> many Post
	// mappedBy = category is come from Post Entity class where we have 
	// private Category category
	// cascade = CascadeType.All -If an entity (parent) has a collection of associated entities (children) 
	// and CascadeType.ALL is specified, when you perform an operation like saving the parent entity, 
	// the associated child entities will also be saved automatically.
	// Similarly, if you delete the parent entity, the associated child entities will also be deleted 
	// automatically.
	
	// if we fetch Category then his all post directly come from database
	
	// Parent(if category deleted then it's all post will also deleted)
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private  List<Post> posts = new ArrayList<>();

}
