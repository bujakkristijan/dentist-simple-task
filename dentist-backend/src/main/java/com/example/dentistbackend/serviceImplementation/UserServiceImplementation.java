package com.example.dentistbackend.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.dentistbackend.model.Appointment;
import com.example.dentistbackend.model.User;
import com.example.dentistbackend.repository.UserRepository;
import com.example.dentistbackend.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
		
	}
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	@Override
	public User delete(User user) {
		if(user == null) 
			throw new IllegalArgumentException("Attempt to delete non-existing course.");
		
		userRepository.delete(user);
		return user;
	}
	@Override 
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override 
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User currentUser = userRepository.findByUsername(currentPrincipalName);
		return currentUser;
	}
	

}
