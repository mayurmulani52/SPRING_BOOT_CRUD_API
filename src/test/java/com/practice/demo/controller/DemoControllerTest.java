package com.practice.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.practice.demo.entity.User;
import com.practice.demo.service.UserService;

@WebMvcTest(DemoControllerTest.class)
class DemoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private User user;

	@BeforeEach
	void setup() {
		user = new User(1, "mayurmulani52", "Mayur", "Mulani", Boolean.TRUE);
	}

	void testSaveUser() throws Exception {
		User user = new User(1, "mayurmulani52", "Mayur", "Mulani", Boolean.TRUE);
		Mockito.when(userService.createUser(user.getUserName(), user.getFirstName(), user.getLastName()))
				.thenReturn(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/users/").contentType(MediaType.APPLICATION_JSON)
						.content("{\r\n" + "  \"userName\": \"mayurmulani52\",\r\n" + "  \"firstName\": \"Mayur\",\r\n"
								+ "  \"lastName\": \"Mulani\",\r\n" + "}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
