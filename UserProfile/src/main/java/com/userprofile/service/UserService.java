package com.userprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.userprofile.model.User;

public interface UserService {
	ResponseEntity<?> addUser(User user);
    List<User> getAllUsers();
    String updateUser(Long userId, User user);
    String deleteUser(Long userId);
    User getUserByFirstName(String firstname);
}
