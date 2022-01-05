package com.project.noteappmain.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.noteappmain.entities.User;
import com.project.noteappmain.handlers.UserAlreadyExistsException;
import com.project.noteappmain.handlers.UserNotFoundException;
import com.project.noteappmain.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	@Transactional
	public User saveUser(User user) {
		User checkUser=userRepository.findUserByEmail(user.getEmail());
		if(checkUser!=null)
			throw new UserAlreadyExistsException();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User updateUser(int userId, User user) {
		Optional<User> userToGet=userRepository.findById(userId);
		if(!userToGet.isPresent())
			throw new UserNotFoundException();
		User userToUpdate=userToGet.get();
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userToUpdate.setUserName(user.getUserName());
		return userRepository.save(userToUpdate);
	}

	@Override
	@Transactional
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		Optional<User> userToGet=userRepository.findById(userId);
		if(!userToGet.isPresent())
			throw new UserNotFoundException();
		return userToGet.get();
	}

	@Override
	@Transactional
	public User getUserByEmail(String email)
	{
		return userRepository.findUserByEmail(email);
	}
	@Override
	@Transactional
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub

		Optional<User> userToGet = userRepository.findById(userId);
		if(!userToGet.isPresent())
			throw new UserNotFoundException();
		User userToDelete = userToGet.get();
	    userRepository.delete(userToDelete);
		
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> allUsers=new LinkedList<User>();
		Iterable<User> users=userRepository.findAll();
		Iterator<User> iterator=users.iterator();
		while(iterator.hasNext())
		{
			allUsers.add(iterator.next());
		}
		return allUsers;
			
	}

}
