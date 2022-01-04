package com.project.noteappmain.services;

import java.util.List;

import com.project.noteappmain.entities.User;

public interface UserService {
	public User saveUser(User user);
	public User updateUser(int userId,User user);
	public User getUser(int userId);
	public User getUserByEmail(String email);
	public void deleteUser(int userId);
	public List<User> getAllUsers();
	
	
}
