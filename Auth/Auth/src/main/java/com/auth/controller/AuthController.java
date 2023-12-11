package com.auth.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.exception.UserNotFound;
import com.auth.jwt.JwtUtils;
import com.auth.request.LoginRequest;
import com.auth.response.JwtResponse;
import com.auth.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			ResponseEntity.ok();
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
		} catch (Exception e) {
			throw new UserNotFound("User Not Found,Please enter correct Credentials");
		}

	}
	@GetMapping("/validate-token")
	public ResponseEntity<Object> validateToken(@RequestParam String token){
		if(token == null || !token.startsWith("Bearer")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		else {
			String realToken = token.substring(7);
			return ResponseEntity.ok(jwtUtils.validateJwtToken(realToken));
		}
		
			
	}
}
