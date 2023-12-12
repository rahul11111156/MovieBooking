package com.user.auth.userdata;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.auth.model.UserData;
import com.user.auth.repository.UserRepository;




@Service
public class UserDataServiceImpl implements UserDataService{
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserData addUser(UserData user) {
		
		return userRepository.save(user);
	}
	
   }

