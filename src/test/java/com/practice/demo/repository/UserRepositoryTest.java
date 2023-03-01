package com.practice.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.practice.demo.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setup() {
		User user = new User(1, "mayurmulani52", "Mayur", "Mulani", Boolean.TRUE);
		entityManager.persist(user);
	}

	@Test
	void testFindByUserId() {

		User userById = userRepository.findById(Long.valueOf(1)).get();
		assertEquals("mayurmulani52", userById.getUserName());

	}

}
