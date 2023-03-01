package com.practice.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.practice.demo.entity.User;
import com.practice.demo.exception.PracticeDemoRunTimeException;
import com.practice.demo.exception.ResourceNotFoundException;
import com.practice.demo.repository.UserRepository;
import com.practice.demo.service.UserService;

import lombok.extern.slf4j.XSlf4j;

@XSlf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers(int page, int size) throws PracticeDemoRunTimeException {

		page = page - 1;
		if (page < 0)
			throw new PracticeDemoRunTimeException("Page index must be greater than zero!");
		else if (size <= 0)
			throw new PracticeDemoRunTimeException("Size index must be greater than zero!");

		Page<User> listOfUsers = userRepository.findAll(PageRequest.of(page, size, Direction.ASC));

		return listOfUsers.getContent();
	}

	@Override
	public Optional<User> findById(long id) throws PracticeDemoRunTimeException {

		Optional<User> user = userRepository.findById(id);

		return Optional.ofNullable(user)
				.orElseThrow(() -> new ResourceNotFoundException(null, String.valueOf(id), user));
	}

	@Override
	public User createUser(String userName, String firstName, String lastName) throws PracticeDemoRunTimeException {

		try {
			User user = userRepository.save(new User(userName, firstName, lastName, Boolean.TRUE));
			return user;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public User updateUser(long id, User user) throws PracticeDemoRunTimeException {
		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isPresent()) {
			User _user = existingUser.get();
			_user.setUserName(user.getUserName());
			_user.setFirstName(user.getFirstName());
			_user.setLastName(user.getLastName());
			_user.setActive(user.isActive());
			return userRepository.save(_user);
		} else {
			throw new PracticeDemoRunTimeException();
		}
	}

	@Override
	public void deleteUser(long id) throws PracticeDemoRunTimeException {

		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}

}
