package com.userprofile.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.userprofile.exception.CustomExceptionHandler;
import com.userprofile.model.User;
import com.userprofile.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/users")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup") 
    public ResponseEntity<Object> addUser(@RequestBody User user) {
    	try {
        return new ResponseEntity<Object>(userService.addUser(user), HttpStatus.OK);
    	}
    	catch(CustomExceptionHandler e) {
    		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    	}
    }

    @GetMapping("/allusers")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<Object>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody User user) {
    	try {
        return new ResponseEntity<Object> (userService.updateUser(userId, user),HttpStatus.OK);
    }
    	catch(CustomExceptionHandler e) {
    		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    	}
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
    	try {
    		return new ResponseEntity<Object>(userService.deleteUser(userId),HttpStatus.OK);
    	}
    	catch(CustomExceptionHandler e) {
    		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    	}
    }

    @GetMapping("/byFirstName/{firstname}")
    public ResponseEntity<Object> getUserByFirstName(@PathVariable String firstname) {
    	try {
    	return new ResponseEntity<Object>(userService.getUserByFirstName(firstname),HttpStatus.OK);
    	}
    	catch(CustomExceptionHandler e) {
    		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    	}
    }
    
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
    	return new ResponseEntity<Object>(userService.getUserByUsername(email),HttpStatus.OK);
    }
    
    	
}