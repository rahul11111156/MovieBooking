package com.user.auth.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.auth.model.User;


public class UserInfoDetails implements UserDetails {


    private long userid;

    public void setUserid(long userid) {
        this.userid = userid;
    }

    private String email;

    @JsonIgnore
    private String password;

    public UserInfoDetails(long userid, String firstName, String lastName, String email, String password) {
        this.userid = userid;
        this.email = email;
        this.password = password;
    }


    public static UserInfoDetails build(User user) {
        return new UserInfoDetails(
        		user.getUserid(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword());
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
