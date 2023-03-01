package com.pracatice.demo.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.demo.repository.UserRepository;
import com.practice.demo.service.UserService;
import com.practice.demo.service.impl.UserServiceImpl;

@SpringBootTest
public class UserServiceMockTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks // auto inject helloRepository
	private UserService userService = new UserServiceImpl();

}
