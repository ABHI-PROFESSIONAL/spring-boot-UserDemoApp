package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.beans.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

import javax.validation.Valid;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUserByid(@PathVariable int id)
	{
		User user=userService.getUserById(id);
		
		if(user==null)
			throw new UserNotFoundException("Id ="+id);

		EntityModel<User> model= EntityModel.of(user);

		WebMvcLinkBuilder linkBuilder=
				linkTo(methodOn(this.getClass()).getUsers());

		model.add(linkBuilder.withRel("all users"));
		return model;
	}
	
	@PostMapping("/users")
	public ResponseEntity createUser(@Valid  @RequestBody User user)
	{
		User savedUser=userService.createUser(user);
		
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("users/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable int id)
	{
		User deleteUser= userService.deleteUser(id);

		if(deleteUser==null)
			throw new UserNotFoundException("id = "+id);

		return ResponseEntity.noContent().build();

	}

}
