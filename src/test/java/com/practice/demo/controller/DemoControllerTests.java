package com.practice.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.practice.demo.entity.User;
import com.practice.demo.repository.UserRepository;
import com.practice.demo.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Mock
	UserRepository userRepository;

	@Mock
	UserService userService;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getHello() throws Exception {

		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/").toString(), String.class);
		assertEquals("Hello, Welcome to Demo Application!", response.getBody());

	}

	@Test
	public void getAllUsersTest() throws Exception {
		List<User> list = new ArrayList<User>();
		User userOne = new User("mayurmulani52", "Mayur", "Mulani", Boolean.TRUE);
		User userTwo = new User("mayurmulani53", "Mayurkumar", "Mulanisdfs", Boolean.TRUE);
		User userThree = new User("mayurmulani54", "Mayursdf", "Mulanisdf", Boolean.TRUE);

		list.add(userOne);
		list.add(userTwo);
		list.add(userThree);
		userRepository.saveAll(list);
		assertEquals(3, list.size());
	}

	@Test
	public void getUserByIdTest() {

		User user = new User("mayurmulani52", "Mayur", "Mulani", Boolean.TRUE);

		userRepository.save(user);

		Optional<User> userData = userRepository.findById(Long.valueOf(0));
		
		verify(userService, times(1)).createUser(user.getUserName(), user.getFirstName(), user.getLastName());
		verify(userService, times(1)).findById(Long.valueOf(1));
	}

	@Test
	public void createUserTest() {
		User user = new User("mayurmulani52", "Mayur", "Mulani", Boolean.TRUE);

		userService.createUser(user.getUserName(), user.getFirstName(), user.getLastName());

		verify(userService, times(1)).createUser(user.getUserName(), user.getFirstName(), user.getLastName());
	}

}
