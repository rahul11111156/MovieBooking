package com.userprofile.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userprofile.Controller.UserProfileController;
import com.userprofile.exception.CustomExceptionHandler;
import com.userprofile.model.User;
import com.userprofile.service.UserService;



@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;
    @Mock
    UserService userService;
    @InjectMocks
    private UserProfileController userController;

    private User user;
    private List<User> userList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User(1L, "Keerti", "Biradar", "keerti@gmail.com","keerti123");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }
 
    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() throws Exception {
        when(userService.addUser(any())).thenReturn(user);
        mockMvc.perform(post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(userService).addUser(any());
    }
    
    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/allusers")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andDo(MockMvcResultHandlers.print());
        verify(userService).getAllUsers();
        verify(userService, times(1)).getAllUsers();

    }
    @Test
    public void givenFirstNameThenShouldReturnRespectiveUser() throws Exception {
        when(userService.getUserByFirstName(any())).thenReturn(userList);
        mockMvc.perform(get("/users/byFirstName/keerti"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void givenUserIdToDeleteThenShouldReturnDeletedUser() throws Exception {
        when(userService.deleteUser(any())).thenReturn(user);
        mockMvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void givenBlogToUpdateThenShouldReturnUpdatedBlog() throws Exception {
        mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
        .andExpect(MockMvcResultMatchers.status().isOk());
        
                
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
