package com.userprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.userprofile.exception.CustomExceptionHandler;
import com.userprofile.model.User;

public interface UserService {
	User addUser(User user) ;
    List<User> getAllUsers();
    String updateUser(Long userId, User user);
    User deleteUser(Long userId);
    List<User> getUserByFirstName(String firstname);
    Optional<User> getUserByUsername(String email);
    
}
