package com.example.dentistbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.dentistbackend.model.User;

@Service
//@Transactional
public interface UserService {
	
	List<User> findAll();
	User save(User user);
	User findOne(Long id);
	User findByUsername(String username);
	User delete(User user);
	
	User getCurrentUser();

}
