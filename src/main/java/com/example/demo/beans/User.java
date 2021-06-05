package com.example.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {


	
	private int id;

	@Size(min = 5)
	private String firstName;


	private String lastName;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	/*public User(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}*/

	/*@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}*/
	
	
	

}
