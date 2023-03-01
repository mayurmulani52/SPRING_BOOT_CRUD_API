package com.practice.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.entity.User;
import com.practice.demo.exception.PracticeDemoRunTimeException;
import com.practice.demo.exception.ResourceNotFoundException;
import com.practice.demo.model.UserRequest;
import com.practice.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DemoController {

	@Autowired
	UserService userService;

	private static final int DEFAULT_CURRENT_PAGE = 0;
	private static final int DEFAULT_CURRENT_PAGESIZE = 10;

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/")
	public String welcome() {
		return "Hello, Welcome to Demo Application!";
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@Valid @RequestParam(value = "page") Optional<Integer> page,
			@Valid @RequestParam(value = "size") Optional<Integer> size) throws PracticeDemoRunTimeException {

		logger.info("Inside the Controller method of getAllUsers!");

		int currentPage = DEFAULT_CURRENT_PAGE;
		if (page.isPresent()) {
			currentPage = page.get();
		}

		int pageSize = DEFAULT_CURRENT_PAGESIZE;
		if (size.isPresent()) {
			pageSize = size.get();
		}

		return new ResponseEntity<>(userService.getAllUsers(currentPage, pageSize), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) throws ResourceNotFoundException {
		logger.info("Inside the Controller method of getUserById!");
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody UserRequest user) throws PracticeDemoRunTimeException {
		logger.info("Inside the Controller method of createUser!");
		return new ResponseEntity<>(userService.createUser(user.getUserName(), user.getFirstName(), user.getLastName()),
				HttpStatus.CREATED);

	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody UserRequest user) {
		logger.info("Inside the Controller method of updateUser!");
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {

		logger.info("Inside the Controller method of deleteUser!");
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
