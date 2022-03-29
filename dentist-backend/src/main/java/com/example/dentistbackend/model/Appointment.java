package com.example.dentistbackend.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
@Entity
public class Appointment{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false) 
	private int phoneNumberId;
	
	@Column(nullable = false) 
	private String firstName;
	
	@Column(nullable = false) 
	private String lastName;
	
	@Column(nullable = false) 
	private String email;
	
	@Column(nullable = false) 
	private Date date;
	
	@Column(nullable = true) 
	private Date endDateWithDurationAdded;
	
	@Column(nullable = false) 
	private String time;
	
	@Column(nullable = false) 
	private String duration;
	
	@ManyToOne
	private User dentist;
	
	@Transient
	private String messageAvailable;
	@Transient
	private String messageAlreadyExist;
	@Transient
	private String messageDateInPast;
	@Transient
	private String messageInvalidInput;
	@Transient
	private String messageSuccessfullyAdded;
	
	public Appointment(int phoneNumberId, String firstName, String lastName, String email, Date date, Date endDateWithDurationAdded, String time, String duration, String messageAvailable, String messageAlreadyExist, String messageDateInPast, String messageInvalidInput, String messageSuccessfullyAdded, User dentist) {
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
		this.dentist = dentist;
	}
	
	public User getDentist() {
		return dentist;
	}

	public void setDentist(User dentist) {
		this.dentist = dentist;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
