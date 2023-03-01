package com.practice.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByActive(boolean active);

	List<User> findByFirstNameContaining(String firstName);

}