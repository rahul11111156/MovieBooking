package com.auth.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.auth.exception.UserNotFound;
import com.auth.model.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = new User();
    	ResponseEntity<User> responseEntity = restTemplate
                .getForEntity("http://localhost:9095/api/users/getByEmail/" + username,
                User.class);
    	
    	user = responseEntity.getBody();
    	System.out.println(user);
    	if(user==null) {
    		throw new UserNotFound("User Not Found with username: " + username);
    	}
        return UserDetailsImpl.build(user);
    }

}
