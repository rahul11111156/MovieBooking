package com.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userdata")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    // Constructors, getters, and setters

    public UserData() {
        // Default constructor for JPA
    }

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

	@Override
	public String toString() {
		return "UserData [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    // Getters and setters

    
}