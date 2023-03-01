package com.practice.demo.service;

import java.util.List;

import com.practice.demo.entity.User;
import com.practice.demo.exception.PracticeDemoRunTimeException;
import com.practice.demo.model.UserRequest;

public interface UserService {
	
	List<User> getAllUsers(int page, int size) throws PracticeDemoRunTimeException;
	
	User findById(long id) throws PracticeDemoRunTimeException;
	
	User createUser(String userName, String firstName, String lastName) throws PracticeDemoRunTimeException;
	
	User updateUser(long id, UserRequest user) throws PracticeDemoRunTimeException;
	
	void deleteUser(long id) throws PracticeDemoRunTimeException;

}
