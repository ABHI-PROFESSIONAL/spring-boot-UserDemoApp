package com.example.demo.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.demo.beans.User;

@Service
@Slf4j
public class UserService {
	
	private static List<User> users= new ArrayList<>();
	
	private static int count=3;
	
	static
	{
		users.add(new User(1, "Jarvis", "Raj"));
		users.add(new User(2, "Abhishek", "Raj"));
		users.add(new User(3, "Ashish", "Raj"));
		
	}
	
	public List<User> getUsers()
	{
		return users;
	}
	
	public User getUserById(int id)
	{
		for(User u:users)
		{
			if(u.getId()==id)
				return u;
		}
		return null;
	}
	
	public User createUser(User user)
	{
		if(user.getId()==0)
			user.setId(users.size()+1);
		users.add(user);
		return user;
	}

	public  User deleteUser(int id)
	{
		log.info("deleteUser called ");
		User deleteUser=null;
		Iterator<User> iterator=users.listIterator();
		while(iterator.hasNext())
		{
			System.out.println("in while...");
			deleteUser=iterator.next();

			if(deleteUser.getId()==id) {
				iterator.remove();
				return deleteUser;
			}
		}

		return null;
	}
	
	public  User updateUser(User user)
	{
		Iterator<User> iterator=users.listIterator();
		while(iterator.hasNext())
		{
			User updateUser=iterator.next();
			//if(updateUser.getId()==user.getId())
				//iterator.s
		}

		return user;
	}
	
	
	

}
