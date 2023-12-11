package com.userprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.userprofile.exception.CustomExceptionHandler;
import com.userprofile.model.User;
import com.userprofile.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    private Gson gson;
    
    private static final String TOPIC = "nutritionapp";

    @Override
     public User addUser(User user)  {
     if(!userRepository.findByEmail(user.getEmail()).isPresent()) {
    	 
    	 userRepository.save(user);
    	 kafkaTemplate.send(TOPIC, gson.toJson(user));
    	 return user;
     }
     else {
    	 throw new CustomExceptionHandler("User Email already exists");
     }
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
        throw new CustomExceptionHandler("No user with id:"+ userId); 
    }
    }

    @Override
    public User deleteUser(Long userId) {
    	Optional<User> user= userRepository.findById(userId);
    	if (user.isPresent()) {
    		userRepository.deleteById(userId);
    		return user.get();
    	}
    	else{
    		throw new CustomExceptionHandler("No user with id:"+ userId);
    	}
    }

    @Override
    public List<User> getUserByFirstName(String firstname) {
    	
       List<User> temp= userRepository.findByFirstname(firstname);
       if(!temp.isEmpty()) {
    	   return temp;
       }
       else {
    	   throw new CustomExceptionHandler("No user with username:"+firstname);
       }
    	
    	
    }
    
    @Override
    public Optional<User> getUserByUsername(String email) {
    	return userRepository.findByEmail(email);
    	}
}
