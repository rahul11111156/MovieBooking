package com.userprofile.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.userprofile.exception.CustomExceptionHandler;
import com.userprofile.model.User;
import com.userprofile.repository.UserRepository;
import com.userprofile.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private User user,user1;
    private List<User> userList=new ArrayList<User>();
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User(1L, "Keerti", "Biradar", "keerti@gmail.com","keerti123");
        optional = Optional.of(user);
    }
    @AfterEach
    public void tearDown() {
        user = null;
    }
    
    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() {
        when(userRepository.save(any())).thenReturn(user);
        assertEquals(user, userService.addUser(user));
        verify(userRepository, times(1)).save(any());
    }
    @Test
    public void givenUserToSaveThenShouldNotReturnSavedUser() {
        when(userRepository.findByEmail(any())).thenThrow(new CustomExceptionHandler("User Email already exists"));
        Assertions.assertThrows(CustomExceptionHandler.class,() -> {
            userService.addUser(user);
        });
    }
    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
        //stubbing the mock to return specific data
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userList1 = userService.getAllUsers();
        assertEquals(userList, userList1);
        verify(userRepository, times(1)).findAll();
    }
    
   
    @Test
    void givenuserIdToDeleteThenShouldReturnDeletedUser() {
    	when(userRepository.findById(anyLong())).thenReturn(optional.of(user));
        User deletedUser = userService.deleteUser(1L);
        assertEquals(1, deletedUser.getUserid());

        verify(userRepository, atMost(2)).findById(anyLong());
    }
    @Test
    public void givenUserToUpdatedThenShouldReturnUpdatedUser() {
    	when(userRepository.findById(anyLong())).thenReturn(optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        assertEquals("User updated successfully", userService.updateUser(anyLong(),user));
        verify(userRepository, times(1)).save(any());
    }


}
