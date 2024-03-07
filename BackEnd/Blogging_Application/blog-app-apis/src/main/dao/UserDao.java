package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.User;

//JpaRepsoitory provide all functionalities to do database operations on user
// have methods like findAll(), findById() etc which we used directly to perform operations on Users
// JpaRepository = interface
// we can create custom methods also

//Abstraction and Loose Coupling: By injecting interfaces instead of concrete implementation classes,
//you promote abstraction and loose coupling between components. The service layer depends on 
//the interface, not a specific implementation. This allows for easier maintenance, testing, and 
//swapping of implementations without affecting the service layer.
public interface UserDao extends JpaRepository<User,Integer>{
	
}
