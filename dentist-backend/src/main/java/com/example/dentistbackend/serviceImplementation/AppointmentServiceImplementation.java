package com.example.dentistbackend.serviceImplementation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.dentistbackend.model.Appointment;
import com.example.dentistbackend.model.CancelHours;
import com.example.dentistbackend.model.User;
import com.example.dentistbackend.service.AppointmentService;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
	
	private ArrayList<Appointment> appointmentList;
	private ArrayList<User> userList;
	private CancelHours cancelHours;
	
	@PostConstruct
	private void init() {
		 appointmentList = new ArrayList<Appointment>();
		 appointmentList.add(new Appointment(1234, "zile", "zivkovic", "emailmarko", new Date(), new Date(), "time neki", "30", "yes", "no", "no", "no", "yes"));
		 appointmentList.add(new Appointment(1234, "pera", "markovic", "emailmarko", new Date(), new Date(), "time neki", "60", "yes", "no", "no", "no", "yes"));
		 
		 userList = new ArrayList<User>();
		 userList.add(new User(1, "Kristijan", "Bujak", "dentist", "cileb411@gmail.com", "exist"));
		 
		 
		 cancelHours = new CancelHours();
		 cancelHours.setHours(24);
		 
		 
		 
		 
	 }
	
	@Override
	public User getUser() {
		//vraca jedinog usera sto ima
		User user = new User();
		
		user = userList.get(0);
		
		return user;
	}
	
	@Override
	public CancelHours getCancelHours() {
		return cancelHours;
	}
	
	@Override
	public CancelHours setNewCancelHours(CancelHours cancelHours) {
		this.cancelHours.setHours(cancelHours.getHours());
		return cancelHours;
	}
	
	@Override
	public List<Appointment> getAllAppointments(){
		List<Appointment> returnAppointmentList = new ArrayList<Appointment>();
		for(Appointment a: appointmentList) {
			returnAppointmentList.add(a);
		}
		return returnAppointmentList;
	}
	
	@Override
	public String save(Appointment appointment){
		appointmentList.add(appointment);
		return "Something";
	}
	
	@Override
	public List<Appointment> getAppointmentsByPhoneNumber(int phoneNumber){
		List<Appointment> returnAppointmentList = new ArrayList<Appointment>();
		for(Appointment a: appointmentList) {
			if(phoneNumber == a.getPhoneNumberId()) {
				returnAppointmentList.add(a);
			}
		}
		return returnAppointmentList;
	}
	
	@Override
	public String checkIsAvailable(Date startDate, Date endDate) {
		String retVal = "yes";
		List<Appointment> allAppointmentList = getAllAppointments();
		for(Appointment a: allAppointmentList) {
			if(a.getDate().before(endDate) && a.getEndDateWithDurationAdded().after(startDate) ) {
				retVal = "no";
				break;
			}else {
				retVal = "yes";
			}
		}
		return retVal;
		
	}
	@Override 
	public String checkIsAlreadyBooked(int phoneNumber) {
		String retVal = "no";
		List<Appointment> allAppointmentList = getAllAppointments();
		for(Appointment a: allAppointmentList) {
			if(a.getPhoneNumberId() == phoneNumber) {
				retVal = "yes";
				break;
			}
			else {
				retVal = "no";
			}
		}
		return retVal;
	}
	
	@Override
	public Appointment findAppointmentByPhoneNumberId(int phoneNumberId) {
		Appointment appointment = new Appointment();
		List<Appointment> allAppointmentList = getAllAppointments();
		for(Appointment a: allAppointmentList) {
			if(a.getPhoneNumberId() == phoneNumberId) {
				appointment = a;
				return appointment;
			}
		}
		return null;
	}
	@Override
	public String delete(int phoneNumberId) {
		
		for(Appointment a: appointmentList) {
			if(a.getPhoneNumberId() == phoneNumberId) {
				appointmentList.remove(a);
				
				return "Deleted";
				
			}
		}
		
		return "NotDeleted";
	}
	
	@Override
	public User findUserById(int dentistId) {
		User user = new User();
		for(User u: userList) {
			if(u.getId() == dentistId) {
				user = u;
				return user;
			}
		}
		return null;
		
	}
	
	@Override
	public Boolean checkIsAvailableToCancel(Appointment appointment) {
		
		boolean availableToCancel;
		
		appointment.getDate().setHours(0);
		appointment.getDate().setMinutes(0);
		
		 Calendar calendarStart = Calendar.getInstance();
		 calendarStart.setTime(appointment.getDate());
		 
		 String hoursAndMinutesParts [] = appointment.getTime().split(":");
		 String hoursString = hoursAndMinutesParts[0];
		 String minutesString = hoursAndMinutesParts[1];
		 int minutes = Integer.valueOf(minutesString);
		 
		 calendarStart.add(Calendar.HOUR_OF_DAY, Integer.valueOf(hoursString));
		 calendarStart.add(Calendar.MINUTE, Integer.valueOf(minutes));
		 
		 Date dateWithTime = calendarStart.getTime();
		 System.out.println("Datum sa vremenom kod cancelovanja: " + dateWithTime);
		
		Instant dateWithTimeInstant = dateWithTime.toInstant();
		Instant now = Instant.now();
		//oduzimam 24h u sekundama od datuma
		
		Instant twentyFourHoursEarlierFromNowInstant = dateWithTimeInstant.minus( cancelHours.hours , ChronoUnit.HOURS );
		//Instant twentyFourHoursEarlierFromNowInstant = dateWithTimeInstant.minus( 24 , ChronoUnit.HOURS );
		//ako je trenutni instant broj u sekundama manji od instanta od datuma umanjen za 24h, znaci da je moguce cancelovati
		if(now.isBefore(twentyFourHoursEarlierFromNowInstant)){
			availableToCancel = true;
			return availableToCancel;
		}else {
			availableToCancel = false;
			return availableToCancel;
		}
		
	}
}
