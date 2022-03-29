package com.example.dentistbackend.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dentistbackend.model.Appointment;
import com.example.dentistbackend.model.CancelHours;
import com.example.dentistbackend.model.User;

@Service
@Transactional
public interface AppointmentService {
	
	List<Appointment> findAll();
	 
	Appointment save(Appointment appointment);
	
	Appointment findOne(Long id);
	
	Appointment delete(Appointment appointment);
	
	//public String save(Appointment appointment);
	
	public List<Appointment> getAllAppointments();
	
	public List<Appointment> getAppointmentsByPhoneNumber(int phoneNumber);
	
	public String checkIsAvailable(Date startDate, Date endDate);
	
	public String checkIsAlreadyBooked(int phoneNumber);
	
	public Appointment findAppointmentByPhoneNumberId(int phoneNumberId);
	
	public String delete(int phoneNumberId);
	
	
	public Boolean checkIsAvailableToCancel(Appointment appointment);
	
	public CancelHours getCancelHours();
	
	public CancelHours setNewCancelHours(CancelHours cancelHours);
	
	public User getUser();

}
