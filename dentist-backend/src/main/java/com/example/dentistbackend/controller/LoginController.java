package com.example.dentistbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dentistbackend.model.Login;
import com.example.dentistbackend.model.LoginJWT;
import com.example.dentistbackend.model.Message;
import com.example.dentistbackend.model.User;
import com.example.dentistbackend.security.JwtUtil;
import com.example.dentistbackend.service.UserService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class LoginController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/welcomeTest", method = RequestMethod.GET)
	public String welcome() {
		return "IDEMO NISSSS!!!";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/welcomeTest123", method = RequestMethod.GET)
	public String welcome123() {
		return "iiiidddeeemmmmmooo!!!";
	}
	
	@PreAuthorize("hasRole('ROLE_DENTIST')")
	@RequestMapping(value = "/welcomeTest1234", method = RequestMethod.GET)
	public String welcome1234() {
		return "iidididiididid!!!";
	}
	
	@PreAuthorize("hasRole('DENTIST')")
	@RequestMapping(value = "/welcomeTest12345", method = RequestMethod.GET)
	public String welcome12345() {
		return "iidididiidididsdssss!!!";
	}
	// OVO RADI!!!!!!!!!!!!!!!
	@PreAuthorize("hasAuthority('DENTIST')")
	@RequestMapping(value = "/welcomeTest123456", method = RequestMethod.GET)
	public String welcome123456() {
		return "iidididiidididsdssss!!!";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/welcomeTestA", method = RequestMethod.GET)
	public String welcomeA() {
		return "oppapappapapappa!!!";
	}
	
	@PreAuthorize("hasAuthority('ROLE_DENTIST')")
	@RequestMapping(value = "/welcomeTest1234567", method = RequestMethod.GET)
	public String welcome1234567() {
		return "iidididiidididsdssss!!!";
	}
	
	
	
	
	@RequestMapping(value = "/loginWithJWT", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginJWT> generateToken(@RequestBody Login login) throws Exception{
		LoginJWT loginJWT = new LoginJWT();
		User user = new User();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
			String token = jwtUtil.generateToken(login.getUsername());
			user = userService.findByUsername(login.getUsername());
			user.setLoginMessage("Exist");
		    loginJWT = new LoginJWT(token, user);
			//return new ResponseEntity<LoginJWT>(loginJWT, HttpStatus.OK);
		} catch (Exception e) {
			loginJWT = new LoginJWT();
			user.setLoginMessage("InvalidUsernameOrPassword");
			loginJWT.setUser(user);
			return new ResponseEntity<LoginJWT>(loginJWT, HttpStatus.OK);
			//throw new Exception("Invalid username or password!");
			
		}
		
		
		return new ResponseEntity<LoginJWT>(loginJWT, HttpStatus.OK);
		
		
		
		//return jwtUtil.generateToken(login.getUsername());
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<LoginJWT> Logout() throws Exception{
		LoginJWT loginJWT = new LoginJWT();
		
		if(userService.getCurrentUser() != null)
		SecurityContextHolder.clearContext();
			
			
		
		
		return new ResponseEntity<LoginJWT>(loginJWT, HttpStatus.OK);
		
		
		
		//return jwtUtil.generateToken(login.getUsername());
		
	}
	
	
		

}
