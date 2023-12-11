package com.auth.security.services;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;



    private long userid;

    public void setUserid(long userid) {
        this.userid = userid;
    }

    private String email;

    @JsonIgnore
    private String password;

    public UserDetailsImpl(long userid, String firstName, String lastName, String email, String password) {
        this.userid = userid;
        this.email = email;
        this.password = password;
    }


    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
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