package com.blog.test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.dao.UserDao;

@SpringBootTest
public class BlogAppApisTest {
   
	@Autowired
	private UserDao userdao;
	
	@Test
	void contextLoads()
	{
		
	}
	
	@Test
	public void repoTest()
	{
		String className = userdao.getClass().getName();
		String packName = userdao.getClass().getPackageName();
		System.out.println(className);  
		// className = jdk.proxy2.$Proxy110 - implementation class of UserRepo(it's the name of UserDao interface(it's implementation className) 
		// this class is dynamically created 
		System.out.println(packName);   
		// packName = jdk.proxy2 (it's package name )
		
	}
}
