package com.example.dentistbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dentistbackend.model.Appointment;
import com.example.dentistbackend.model.User;
import com.example.dentistbackend.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'DENTIST')")
	@RequestMapping(value= "/getAllDentists", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllAppointmentList(){
		
		List<User> dentistList = new ArrayList<User>();
		List<User> allUserList = userService.findAll();
		for(User u: allUserList) {
			if(u.getRole().equals("DENTIST")) {
				dentistList.add(u);
			}
		}
		//System.out.println(allAppointmentList.get(0).getFirstName() + " " + allAppointmentList.get(0).getMessageAvailable());
		return new ResponseEntity<List<User>>(dentistList, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/createUser", method =RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		if(user == null) {
			user = new User();
			user.getErrorMessage().setUserInvalidInput("yes");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		if(user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getFirstName() == null ||
				user.getFirstName().trim().isEmpty() || user.getRole() == null || user.getRole().trim().isEmpty() || 
					user.getUsername() == null || user.getUsername().equals("") ) {
			user = new User();
			user.getErrorMessage().setUserInvalidInput("yes");
			return new ResponseEntity<User>(user, HttpStatus.OK);
			
		}
		if(userService.findByUsername(user.getUsername())!= null) {
			user.getErrorMessage().setUsernameAlreadyExist("yes");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		else {
			userService.save(user);
			user.getErrorMessage().setSuccessAdded("yes");
			
			
		}
		
			
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	

}
