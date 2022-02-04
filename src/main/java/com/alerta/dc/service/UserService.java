package com.alerta.dc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alerta.dc.model.JwtRequest;
import com.alerta.dc.model.User;
import com.alerta.dc.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> fetchUsers() {
		
		List<User> users = userRepository.findAll();
		
		for(User user: users) {
			user.setPassword("******");
		}
		
		return users;
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User getAuthUser(JwtRequest jwtRequest){		
		User user = userRepository.findByAuth(jwtRequest.getUsername(), jwtRequest.getPassword());
		
		if(user != null) {
			return user;
		}
		return null;
	}
}
