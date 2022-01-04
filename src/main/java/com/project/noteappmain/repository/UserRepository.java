package com.project.noteappmain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.noteappmain.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findUserByEmail(String email);
}
