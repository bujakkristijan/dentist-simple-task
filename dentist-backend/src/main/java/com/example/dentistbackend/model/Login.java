package com.example.dentistbackend.model;

public class Login {
	
	private int dentistId;
	
	public Login() {
		
	}
	public Login(int dentistId) {
		super();
		this.dentistId = dentistId;
	}

	public int getDentistId() {
		return dentistId;
	}

	public void setDentistId(int dentistId) {
		this.dentistId = dentistId;
	}

	
	
	

}
