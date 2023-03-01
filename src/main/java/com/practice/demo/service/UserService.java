package com.practice.demo.service;

import java.util.List;
import java.util.Optional;

import com.practice.demo.entity.User;
import com.practice.demo.exception.PracticeDemoRunTimeException;

public interface UserService {
	
	List<User> getAllUsers(int page, int size) throws PracticeDemoRunTimeException;
	
	Optional<User> findById(long id) throws PracticeDemoRunTimeException;
	
	User createUser(String userName, String firstName, String lastName) throws PracticeDemoRunTimeException;
	
	User updateUser(long id, User user) throws PracticeDemoRunTimeException;
	
	void deleteUser(long id) throws PracticeDemoRunTimeException;

}
