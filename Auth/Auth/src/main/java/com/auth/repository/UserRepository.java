package com.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.model.UserData;


public interface UserRepository extends JpaRepository<UserData, Long> {
	
	}