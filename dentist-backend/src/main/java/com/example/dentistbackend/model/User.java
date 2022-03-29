package com.example.dentistbackend.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String firstName;
	
	private String lastName;
	private String username;
	private String password;
	private String role;
	private String email;
	
	@Transient // ne pravi u bazu ovu kolonu
	private String loginMessage;
	
	@Transient
	private Message errorMessage;
	
	
	

	
	

	@JsonIgnore
	@OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Appointment> appointments = new ArrayList<Appointment>();
	
	
	public User() {
		
	}

	

	public User(Long id, String firstName, String lastName, String username, String password, String role, String email, String loginMessage) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.loginMessage = loginMessage;
		
	}
	
	public User(Long id, String firstName, String lastName, String username, String password, String role, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		
		
		
	}
	
	public Message getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(Message errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	} 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	

}
