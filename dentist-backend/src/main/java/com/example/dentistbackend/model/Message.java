package com.example.dentistbackend.model;

public class Message {
	
	public String availableToCancel;
	public String cancelHoursMessage;
	
	public String usernameAlreadyExist;
	public String userInvalidInput;
	public String successAdded;
	
	

	

	public String getSuccessAdded() {
		return successAdded;
	}

	public void setSuccessAdded(String successAdded) {
		this.successAdded = successAdded;
	}

	public String getUserInvalidInput() {
		return userInvalidInput;
	}

	public void setUserInvalidInput(String userInvalidInput) {
		this.userInvalidInput = userInvalidInput;
	}

	public Message() {
		
	}
	
	public String getUsernameAlreadyExist() {
		return usernameAlreadyExist;
	}

	public void setUsernameAlreadyExist(String usernameAlreadyExist) {
		this.usernameAlreadyExist = usernameAlreadyExist;
	}
	
	public String getCancelHoursMessage() {
		return cancelHoursMessage;
	}

	public void setCancelHoursMessage(String cancelHoursMessage) {
		this.cancelHoursMessage = cancelHoursMessage;
	}

	public String getAvailableToCancel() {
		return availableToCancel;
	}

	public void setAvailableToCancel(String availableToCancel) {
		this.availableToCancel = availableToCancel;
	}

	public Message(String availableToCancel) {
		super();
		this.availableToCancel = availableToCancel;
	}

}
