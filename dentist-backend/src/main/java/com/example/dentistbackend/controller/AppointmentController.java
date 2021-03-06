package com.example.dentistbackend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dentistbackend.email.EmailSenderService;
import com.example.dentistbackend.model.Appointment;
import com.example.dentistbackend.model.CancelHours;
import com.example.dentistbackend.model.Login;
import com.example.dentistbackend.model.Message;
import com.example.dentistbackend.model.User;
import com.example.dentistbackend.security.JwtUtil;
import com.example.dentistbackend.service.AppointmentService;
import com.example.dentistbackend.service.UserService;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired 
	private EmailSenderService emailSenderService;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DENTIST')")
	@RequestMapping(value= "/getAllAppointments", method = RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getAllAppointmentList(){
		//List<Appointment> allAppointmentList = new ArrayList<Appointment>();
		//allAppointmentList = appointmentService.getAllAppointments();
		List<Appointment> allAppointmentList = appointmentService.findAll();
		//System.out.println(allAppointmentList.get(0).getFirstName() + " " + allAppointmentList.get(0).getMessageAvailable());
		return new ResponseEntity<List<Appointment>>(allAppointmentList, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DENTIST')")
	@RequestMapping(value= "/getMyAppointments", method = RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getMyAppointmentList(){
		
		User currentUser = userService.getCurrentUser();
		List<Appointment> allAppointmentList = appointmentService.findAll();
		List<Appointment> myAppointmentList =new ArrayList<Appointment>();
		for(Appointment a: allAppointmentList) {
			if(a.getDentist().getId() == currentUser.getId()) {
				myAppointmentList.add(a);
			}
		}
		return new ResponseEntity<List<Appointment>>(myAppointmentList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAppointmentByPhoneNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentsByPhoneNumber(@RequestBody Appointment appointment){
		
		//List<Appointment> returntAppointmentList = new ArrayList<Appointment>();
		List<Appointment> returntAppointmentList = appointmentService.getAppointmentsByPhoneNumber(appointment.getPhoneNumberId());
		return new ResponseEntity<List<Appointment>>(returntAppointmentList,  HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DENTIST')")
	@RequestMapping(value= "/getCancelHours", method = RequestMethod.GET)
	public ResponseEntity<CancelHours> getCancelHours(){
		CancelHours cancelHours = new CancelHours();
		cancelHours = appointmentService.getCancelHours();
		return new ResponseEntity<CancelHours>(cancelHours, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN', 'DENTIST')")
	@RequestMapping(value = "/changeCancelHours", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> changeCancelHours(@RequestBody CancelHours cancelHours){
		Message message = new Message();
		
		if(cancelHours == null) {
			message.setCancelHoursMessage("failed");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		
		appointmentService.setNewCancelHours(cancelHours);
		message.setCancelHoursMessage("success");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/cancelAppointment/{phoneNumberId}", method = RequestMethod.DELETE)
	public ResponseEntity<Message> cancelAppointment(@PathVariable int phoneNumberId){
		Appointment appointment = appointmentService.findAppointmentByPhoneNumberId(phoneNumberId);
		Message message = new Message();
		if(appointment == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
			
		}
		boolean availableToCancel = appointmentService.checkIsAvailableToCancel(appointment);
		if(availableToCancel == true) {
			//appointmentService.delete(phoneNumberId);
			appointmentService.delete(appointment);
			message.setAvailableToCancel("yes");
			//dodajem i vreme na datum jer je bilo od 00:00
			 Calendar calendarStart = Calendar.getInstance();
			 calendarStart.setTime(appointment.getDate());
			 String hoursAndMinutesParts [] = appointment.getTime().split(":");
			 String hoursString = hoursAndMinutesParts[0];
			 String minutesString = hoursAndMinutesParts[1];
			 int onlyMinutes = Integer.valueOf(minutesString);
			 calendarStart.add(Calendar.HOUR_OF_DAY, Integer.valueOf(hoursString));
			 calendarStart.add(Calendar.MINUTE, Integer.valueOf(onlyMinutes));
			 Date startDate = calendarStart.getTime();
			 appointment.setDate(startDate);
			//email pacijentu
			emailSenderService.SendSimpleEmail(appointment.getEmail(), 
    				"Your appointment that starts at: " + appointment.getDate() + " and ends at: " + appointment.getEndDateWithDurationAdded() + ", with duration: " + appointment.getDuration() + " minutes is canceled successfully!" ,
    				"Cancelation appointment confirmation");
			//email zubaru
			emailSenderService.SendSimpleEmail(appointment.getDentist().getEmail(), 
    				"Your appointment that starts at: " + appointment.getDate() + " and ends at: " + appointment.getEndDateWithDurationAdded() + ", with duration: " + appointment.getDuration() + " minutes, booked by: " + appointment.getFirstName() + " " + appointment.getLastName() + ", phone number: " + appointment.getPhoneNumberId() + " is canceled!" ,
    				"Cancelation appointment confirmation");
			
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		else if(availableToCancel == false){
			
			message.setAvailableToCancel("no");
			
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody Login login){
		User user = new User();
		if(login == null)
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		
		try {
			user = userService.findByUsername(login.getUsername());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		if(user == null) {
			user = new User();
			user.setLoginMessage("NotExist");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		else if (user!=null && !user.getPassword().equals(login.getPassword())) {
			user.setLoginMessage("InvalidPassword");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		else if(user!=null && user.getPassword().equals(login.getPassword())) {
			user.setLoginMessage("Exist");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/createAppointment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) throws ParseException{
		
		if(appointment == null) {
			appointment = new Appointment();
			appointment.setMessageInvalidInput("yes");
	    	return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
		}
		
		
		if(appointment.getPhoneNumberId() == 0|| appointment.getFirstName() == null || appointment.getFirstName().equals("") || appointment.getLastName() == null || appointment.getLastName().equals("") || 
				appointment.getEmail() == null || appointment.getEmail().equals("") || appointment.getDate() == null  || appointment.getDuration() == null || appointment.getDuration().equals("")
				|| appointment.getTime() == null || appointment.getTime().equals("")){
			//appointment = new Appointment(); // mozda i ne mora
			appointment.setMessageInvalidInput("yes");
	    	return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
			
		}
		
		// setujemo sate i minute na 0 jer kada je zauzet termin, setuju se sati i minuti za 10 sati vise umesto 00:00:00, ne znam sto to!!!!!
		appointment.getDate().setHours(0);
		appointment.getDate().setMinutes(0);
		appointment.setMessageDateInPast("no");
		appointment.setMessageInvalidInput("no");
		appointment.setMessageSuccessfullyAdded("no");
		
		//LocalDateTime myDateObj = appointment.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		//DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    //String formattedDate = myDateObj.format(myFormatObj);
	    //System.out.println("Formateddate:" + formattedDate);
	    //Date dateZaSetovanje = new SimpleDateFormat("dd-MM-yyyy").parse(formattedDate);
	    //System.out.println("DateZaSetovanje:" + dateZaSetovanje);
	    //appointment.setDate(dateZaSetovanje);
	    System.out.println("Appointment start date:" + appointment.getDate().toString());
	    //System.out.println("Appointment end date:" + appointment.getEndDateWithDurationAdded().toString());
	    Calendar calendarEnd = Calendar.getInstance();
	    calendarEnd.setTime(appointment.getDate());
	    
	    Calendar calendarStart = Calendar.getInstance();
	    calendarStart.setTime(appointment.getDate());
	    
	    String hoursAndMinutesParts [] = appointment.getTime().split(":");
	    String hoursString = hoursAndMinutesParts[0];
	    String minutesString = hoursAndMinutesParts[1];
	    int onlyMinutes = Integer.valueOf(minutesString);
	    int minutesAndDuration = Integer.valueOf(minutesString) + Integer.valueOf(appointment.getDuration());
	    calendarEnd.add(Calendar.HOUR_OF_DAY, Integer.valueOf(hoursString));
	    calendarEnd.add(Calendar.MINUTE, Integer.valueOf(minutesAndDuration));
	    
	    //string provera debug
	    String calendarEndString = calendarEnd.toString();
	    calendarStart.add(Calendar.HOUR_OF_DAY, Integer.valueOf(hoursString));
	    calendarStart.add(Calendar.MINUTE, Integer.valueOf(onlyMinutes));
	    
	    String calendarStartString = calendarStart.toString();
	    
	    //System.out.println("Sabran calendar sa vremenom i minutima: " + calendarEnd.getTime());
	    //Date startDate = appointment.getDate();
	    //System.out.println("Start date koji prosledjujem: " + startDate);
	    Date startDate = calendarStart.getTime();
	    Date endDate = calendarEnd.getTime();
	    
	    if(startDate.before(new Date())) {
	    	appointment.setMessageDateInPast("yes");
	    	return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
	    }
	    
	    // string provera debug
	    String startDateString = startDate.toString();
	    String endDateString = endDate.toString();
	    appointment.setDate(startDate);
	    appointment.setEndDateWithDurationAdded(endDate);
	    System.out.println("Pocetak datum sa vremenom bez duration: " + appointment.getDate());
	    System.out.println("Zavrsetak datuma sa vremenom i duration: " + appointment.getEndDateWithDurationAdded());
	    
	    if((appointmentService.checkIsAvailable(startDate, endDate)).equals("yes")){
	    	
	    	//appointmentService.save(appointment);
	    	appointment.setMessageAvailable("yes");
	    	if(appointmentService.checkIsAlreadyBooked(appointment.getPhoneNumberId()).equals("no")){
	    		appointment.setMessageAlreadyExist("no");
	    		//appointmentService.save(appointment);
	    		boolean flagInvalidEmail = false;
	    		//slanje email-a 
	    		try {
	    			//email pacijentu
	    			emailSenderService.SendSimpleEmail(appointment.getEmail(), 
		    				"Your appointment starts at: " + appointment.getDate() + " and ends at: " + appointment.getEndDateWithDurationAdded() + ", with duration: " + appointment.getDuration() + " minutes" ,
		    				"Booked appointment confirmation");
	    			//email zubaru
	    			emailSenderService.SendSimpleEmail(appointment.getDentist().getEmail(), 
		    				"You have new appointment that starts at: " + appointment.getDate() + " and ends at: " + appointment.getEndDateWithDurationAdded() + ", with duration: " + appointment.getDuration() + " minutes, booked by: " + appointment.getFirstName() + " " + appointment.getLastName() + ", phone number: " + appointment.getPhoneNumberId() ,
		    				"Booked appointment confirmation");
	    			
	    			
				} catch (Exception e) {
					flagInvalidEmail = true;
					System.out.println("Invalid email");
					appointment.setMessageInvalidInput("yes");
					return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
				}
	    		if(flagInvalidEmail == false) {
	    			appointment.setMessageSuccessfullyAdded("yes");
	    			appointmentService.save(appointment);
	    			return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
	    		}
	    		
	    		
	    		return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
	    	}
	    	else {
	    		appointment.setMessageAlreadyExist("yes");
	    		return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
	    	}
	    	
	    }
	    else {
	    	appointment.setMessageAvailable("no");
	    	//appointment.getDate().setHours(0);
	    	//appointment.getDate().setMinutes(0);
	    	//calendarEnd = Calendar.getInstance();
	    	//calendarStart = Calendar.getInstance();
	    	//minutesAndDuration = 0;
	    	//onlyMinutes = 0;
	    	return new ResponseEntity<Appointment>(appointment,  HttpStatus.OK);
	    }
	    
		
		
		
	}
}
