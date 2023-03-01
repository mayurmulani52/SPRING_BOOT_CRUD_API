package com.practice.demo.service;

import java.util.List;

import com.practice.demo.entity.User;
import com.practice.demo.exception.PracticeDemoRunTimeException;

public interface UserService {
	
	List<User> getAllUsers(int page, int size) throws PracticeDemoRunTimeException;

}
