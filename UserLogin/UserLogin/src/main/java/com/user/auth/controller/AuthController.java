package com.user.auth.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.auth.exception.UserNotFound;
import com.user.auth.model.User;
import com.user.auth.request.LoginRequest;
import com.user.auth.response.JwtResponse;
import com.user.auth.service.JwtService;


@RestController
@RequestMapping("auth/user")
public class AuthController 
{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody LoginRequest user)
	{
		try {
			
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtService.generateToken(user.getUsername());

	        ResponseEntity.ok();
	        return ResponseEntity.ok(new JwtResponse(jwt,
	                user.getUsername()));
		} catch (Exception e) {
			throw new UserNotFound("User Not Found,Please Enter Correct Credentials");
		}
		
	}
	
	@GetMapping("/validate-token")
	public ResponseEntity<Object> validateToken(@RequestParam String token){
		if(token == null || !token.startsWith("Bearer")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		else {
			String realToken = token.substring(7);
			return ResponseEntity.ok(jwtService.validateJwtToken(realToken));
		}
		
			
	}
}

