package com.user.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.user.auth.config.UserInfoDetails;
import com.user.auth.model.User;


@Component
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = new User();
    	ResponseEntity<User> responseEntity = restTemplate
                .getForEntity("http://localhost:9095/api/users/getByEmail/" + username,
                		User.class);
    	user = responseEntity.getBody();
        return UserInfoDetails.build(user);

    }
}
