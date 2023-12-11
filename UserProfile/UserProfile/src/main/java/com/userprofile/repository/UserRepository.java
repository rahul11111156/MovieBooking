package com.userprofile.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userprofile.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByFirstname(String firstname);
	
	Optional<User> findByEmail(String email);
	
	
}