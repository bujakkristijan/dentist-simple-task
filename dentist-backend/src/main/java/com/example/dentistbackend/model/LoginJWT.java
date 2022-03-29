package com.example.dentistbackend.model;

public class LoginJWT {
	
	private String token;
	private User user;
	
	public LoginJWT() {
		
	}

	public LoginJWT(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
