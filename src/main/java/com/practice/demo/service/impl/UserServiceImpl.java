package com.practice.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.practice.demo.entity.User;
import com.practice.demo.exception.PracticeDemoRunTimeException;
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

}
