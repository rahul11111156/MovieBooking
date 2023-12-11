package com.userprofile.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userprofile.model.User;
import com.userprofile.service.UserService;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
    	String result = userService.deleteUser(userId);
    	return ResponseEntity.ok(result);
    }

    @GetMapping("/byFirstName/{firstname}")
    public ResponseEntity<User> getUserByFirstName(@PathVariable String firstname) {
    	User user = userService.getUserByFirstName(firstname);
    	if (user != null) {
    		return ResponseEntity.ok(user);
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    	
}