package com.auth.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.auth.exception.UserNotFound;
import com.auth.model.User;
import com.auth.security.services.UserDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsername_UserFound_ReturnsUserDetails() {
        // Arrange
        String username = "testUser";
        User mockUser = new User(1L, "John", "Doe", username, "password");
        when(restTemplate.getForEntity(anyString(), eq(User.class)))
            .thenReturn(new ResponseEntity<>(mockUser, HttpStatus.OK));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword()); 
//        assertEquals(1L, ((UserDetailsImpl) userDetails).getUserid());
//        assertEquals("John", ((UserDetailsImpl) userDetails).getFirstName());
//        assertEquals("Doe", ((UserDetailsImpl) userDetails).getLastName());
        
    }

    @Test
    public void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        // Arrange
        String username = "nonExistentUser";
        when(restTemplate.getForEntity(anyString(), eq(User.class)))
            .thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        // Act and Assert
        assertThrows(UserNotFound.class, () -> userDetailsService.loadUserByUsername(username));
    }

//    @Test
//    public void loadUserByUsername_RestTemplateError_ThrowsUsernameNotFoundException() {
//        // Arrange
//        String username = "errorUser";
//        when(restTemplate.getForEntity(anyString(), eq(User.class)))
//            .thenThrow(new RestClientException("RestTemplate error"));
//
//        // Act and Assert
//        assertThrows(UserNotFound.class, () -> userDetailsService.loadUserByUsername(username));
//    }

    @Test
    public void loadUserByUsername_RestTemplateReturnsServerError_ThrowsUsernameNotFoundException() {
        // Arrange
        String username = "serverErrorUser";
        when(restTemplate.getForEntity(anyString(), eq(User.class)))
            .thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

        // Act and Assert
        assertThrows(UserNotFound.class, () -> userDetailsService.loadUserByUsername(username));
    }

    @Test
    public void loadUserByUsername_RestTemplateReturnsUnauthorized_ThrowsUsernameNotFoundException() {
        // Arrange
        String username = "unauthorizedUser";
        when(restTemplate.getForEntity(anyString(), eq(User.class)))
            .thenReturn(new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED));

        // Act and Assert
        assertThrows(UserNotFound.class, () -> userDetailsService.loadUserByUsername(username));
    }
}

