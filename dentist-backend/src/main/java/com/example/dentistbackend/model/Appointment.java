package com.example.dentistbackend.model;

import java.util.Date;

public class Appointment {
	
	private int phoneNumberId;
	private String firstName;
	private String lastName;
	private String email;
	private Date date;
	private Date endDateWithDurationAdded;
	private String time;
	private String duration;
	private String messageAvailable;
	private String messageAlreadyExist;
	private String messageDateInPast;
	private String messageInvalidInput;
	private String messageSuccessfullyAdded;
	
	public Appointment(int phoneNumberId, String firstName, String lastName, String email, Date date, Date endDateWithDurationAdded, String time, String duration, String messageAvailable, String messageAlreadyExist, String messageDateInPast, String messageInvalidInput, String messageSuccessfullyAdded) {
		super();
		this.phoneNumberId = phoneNumberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		this.endDateWithDurationAdded = endDateWithDurationAdded;
		this.time = time;
		this.duration = duration;
		this.messageAvailable = messageAvailable;
		this.messageAlreadyExist = messageAlreadyExist;
		this.messageDateInPast = messageDateInPast;
		this.messageInvalidInput = messageInvalidInput;
		this.messageSuccessfullyAdded = messageSuccessfullyAdded;
	}
	public String getMessageSuccessfullyAdded() {
		return messageSuccessfullyAdded;
	}
	public void setMessageSuccessfullyAdded(String messageSuccessfullyAdded) {
		this.messageSuccessfullyAdded = messageSuccessfullyAdded;
	}
	public String getMessageInvalidInput() {
		return messageInvalidInput;
	}
	public void setMessageInvalidInput(String messageInvalidInput) {
		this.messageInvalidInput = messageInvalidInput;
	}
	public String getMessageDateInPast() {
		return messageDateInPast;
	}
	public void setMessageDateInPast(String messageDateInPast) {
		this.messageDateInPast = messageDateInPast;
	}
	public String getMessageAlreadyExist() {
		return messageAlreadyExist;
	}
	public void setMessageAlreadyExist(String messageAlreadyExist) {
		this.messageAlreadyExist = messageAlreadyExist;
	}
	public String getMessageAvailable() {
		return messageAvailable;
	}
	public void setMessageAvailable(String messageAvailable) {
		this.messageAvailable = messageAvailable;
	}
	public Date getEndDateWithDurationAdded() {
		return endDateWithDurationAdded;
	}
	public void setEndDateWithDurationAdded(Date endDateWithDurationAdded) {
		this.endDateWithDurationAdded = endDateWithDurationAdded;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Appointment() {
		super();
	}
	public int getPhoneNumberId() {
		return phoneNumberId;
	}
	public void setPhoneNumberId(int phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
