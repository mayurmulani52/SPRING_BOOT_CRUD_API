package com.practice.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.practice.demo.entity.User;
import com.practice.demo.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

	@BeforeEach
	void setup() {

		Optional<User> user = Optional.of(new User(1, "mayurmulani52", "Mayur", "Mulani", Boolean.TRUE));
		Mockito.when(userRepository.findById(Long.valueOf(1))).thenReturn(user);
	}

	@Test
	public void testFindById_Success() {

		String userName = "mayurmulani52";

		User userById = userService.findById(1);

		assertEquals(userName, userById.getUserName());
	}

}
