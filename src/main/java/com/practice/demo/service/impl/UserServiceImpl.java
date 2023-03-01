package com.practice.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.practice.demo.entity.User;
import com.practice.demo.exception.PracticeDemoRunTimeException;
import com.practice.demo.exception.ResourceNotFoundException;
import com.practice.demo.model.UserRequest;
import com.practice.demo.repository.UserRepository;
import com.practice.demo.service.UserService;

import lombok.extern.slf4j.XSlf4j;

@XSlf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public List<User> getAllUsers(int page, int size) throws PracticeDemoRunTimeException {

		logger.info("Inside the method of getAllUsers!");

		if (page < 0)
			throw new PracticeDemoRunTimeException("Page index must be greater than zero!");
		else if (size <= 0)
			throw new PracticeDemoRunTimeException("Size index must be greater than zero!");

		Page<User> listOfUsers = userRepository.findAll(PageRequest.of(page, size, Direction.ASC, "id"));

		return listOfUsers.getContent();
	}

	@Override
	public User findById(long id) throws ResourceNotFoundException {
		logger.info("Inside the method of findById!");

		Optional<User> user = userRepository.findById(id);

		try {
			return user.get();

		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			throw new ResourceNotFoundException("User does not exist against id :" + id, e);
		}

	}

	@Override
	public User createUser(String userName, String firstName, String lastName) throws PracticeDemoRunTimeException {

		logger.info("Inside the method of createUser!");
		try {
			User user = userRepository.save(new User(userName, firstName, lastName, Boolean.TRUE));
			return user;
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			throw e;
		}

	}

	@Override
	public User updateUser(long id, UserRequest user) throws PracticeDemoRunTimeException {

		logger.info("Inside the method of updateUser!");
		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isPresent()) {
			User _user = existingUser.get();
			_user.setUserName(user.getUserName());
			_user.setFirstName(user.getFirstName());
			_user.setLastName(user.getLastName());
			return userRepository.save(_user);
		} else {
			logger.error("User not found against ID!");
			throw new PracticeDemoRunTimeException("User not found against ID!");
		}
	}

	@Override
	public void deleteUser(long id) throws PracticeDemoRunTimeException {

		logger.info("Inside the method of deleteUser!");
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getStackTrace().toString());
			throw e;
		}
	}

}
