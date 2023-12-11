package com.userprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userprofile.model.User;
import com.userprofile.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> addUser(User user) {
//		if (userRepository.existsByFirstname(user.getFirstname())) {
//			return new ResponseEntity<>("Username is already exists!", HttpStatus.BAD_REQUEST);
//		}

		if (userRepository.existsByEmail(user.getEmail())) {
			return new ResponseEntity<>("Email already exists!", HttpStatus.BAD_REQUEST);
		}
		
		userRepository.save(user);
		return new ResponseEntity<>("User sucessfully registerd!", HttpStatus.OK);
    }
		
	@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String updateUser(Long userId, User user) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setFirstname(user.getFirstname());
            existingUser.setFirstname(user.getFirstname());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
             userRepository.save(existingUser);
             return "User updated successfully";
        } else {
        return "user not found"; 
    }
    }

    @Override
    public String deleteUser(Long userId) {
    	if (!userRepository.existsById(userId)) {
    		return "User not found";
    	}
        userRepository.deleteById(userId);
        return "User deleted sucessfully";
    	}

    @Override
    public User getUserByFirstName(String firstname) {
        return userRepository.findByFirstname(firstname);
    }
}
