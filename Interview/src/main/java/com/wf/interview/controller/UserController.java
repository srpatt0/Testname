package com.wf.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.interview.entity.User;
import com.wf.interview.exception.StudentNotFoundException;
import com.wf.interview.exception.model.ExceptionResponse;
import com.wf.interview.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService service;
	
	// THis method get all users
	
	@GetMapping("/userslist")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users  = this.service.getAll();	
		
		
		ResponseEntity<List<User>> response = 
				new ResponseEntity<List<User>>(users, HttpStatus.OK);
		return response;
	}
	
	//This method is useful to search by ID
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User user = this.service.getById(id);
		
		if(user == null) {
			throw  new StudentNotFoundException("User not Found with Id-" + id);
		}
		
		ResponseEntity<User> response = 
				new ResponseEntity<User>(user, HttpStatus.OK);
		return response;
	}
	
	//This method is useful to add a user
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User addedUser = this.service.add(user);
		ResponseEntity<User> response = 
				new ResponseEntity<User>(addedUser, HttpStatus.OK);
		return response;
	}
	
	
	// This method is useful to update the user
	@PutMapping("/users")
	public ResponseEntity<User> editStudent(@RequestBody User user) {
		User updatedUser = this.service.update(user);
		ResponseEntity<User> response = 
				new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		return response;
	}
	
	// This method is useful to delete
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteStudent(@PathVariable("id") Long id) {
		User user = this.service.delete(id);
		if(user == null) {
			throw  new StudentNotFoundException("Student not Found with Id-" + id);
		}
		ResponseEntity<User> response = 
				new ResponseEntity<User>(user, HttpStatus.OK);
		return response;
	}
	
	// Exception Handler Method
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(StudentNotFoundException ex) {
		ExceptionResponse exResponse =
				new ExceptionResponse();
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.NOT_FOUND);
		return response;
	}
	
	// Exception Handler Method
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handler(Exception ex) {
		ExceptionResponse exResponse =
				new ExceptionResponse();
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.BAD_REQUEST);
		return response;
	}

		private String interviewName;
	
	
}
