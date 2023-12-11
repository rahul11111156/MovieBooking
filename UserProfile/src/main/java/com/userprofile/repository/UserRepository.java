package com.userprofile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userprofile.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByFirstname(String firstname);
	
	boolean existsByEmail(String email);
	
	boolean existsByFirstname(String firstname);
	
	
}