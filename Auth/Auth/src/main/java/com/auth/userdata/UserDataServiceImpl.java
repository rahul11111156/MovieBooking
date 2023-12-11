package com.auth.userdata;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.exception.UserNotFound;
import com.auth.model.UserData;
import com.auth.repository.UserRepository;


@Service
public class UserDataServiceImpl implements UserDataService{
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserData addUser(UserData user) {
		
		return userRepository.save(user);
	}
	
   }

