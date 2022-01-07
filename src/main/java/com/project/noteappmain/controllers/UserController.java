package com.project.noteappmain.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.noteappmain.dto.SuccessMessage;
import com.project.noteappmain.entities.User;
import com.project.noteappmain.services.UserServiceImpl;

@RestController
@RequestMapping(path = "/noteuser")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;

	@GetMapping
	public ResponseEntity<?> getUser(HttpServletRequest request)
	{
		int userId=(int)request.getAttribute("userId");
		User user= userService.getUser(userId);
		return new ResponseEntity<>(new SuccessMessage("User fetched",true,user),HttpStatus.ACCEPTED);
	}
	@GetMapping("/allUsers")
	public ResponseEntity<?> getUsers()
	{
		List<User> users= userService.getAllUsers();
		return new ResponseEntity<>(new SuccessMessage("Users fetched",true,users),HttpStatus.ACCEPTED);
	}
	@PostMapping
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user)
	{
		
		userService.saveUser(user);
		return new ResponseEntity<>(new SuccessMessage("User created",true),HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<?> updateUser(HttpServletRequest request,@Valid @RequestBody User user)
	{
		int userId=(int)request.getAttribute("userId");
		user.setUserId(userId);
		User updatedUser=userService.updateUser(user.getUserId(), user);
		return new ResponseEntity<>(new SuccessMessage("User updated",true,updatedUser),HttpStatus.CREATED);
		
	}
	@DeleteMapping
	public ResponseEntity<?> deleteUser(HttpServletRequest request)
	{
		int userId=(int)request.getAttribute("userId");
		userService.deleteUser(userId);
		return new ResponseEntity<>(new SuccessMessage("User deleted",true),HttpStatus.CREATED);
		
	}
	
}
