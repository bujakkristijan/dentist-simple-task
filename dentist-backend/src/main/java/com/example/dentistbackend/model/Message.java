package com.example.dentistbackend.model;

public class Message {
	
	public String availableToCancel;
	public String cancelHoursMessage;
	
	

	public Message() {
		
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
