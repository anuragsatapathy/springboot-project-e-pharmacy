package com.epharm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epharm.entity.User;
import com.epharm.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean RegisterUser(String email,String password) {
		if(userRepository.findByEmail(email) != null) {
			return false;
		}
		User user = new User();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		return true;
			
	}
	
	public boolean loginUser(String email, String password) {
		
		User user = userRepository.findByEmail(email);
		if(user != null && passwordEncoder.matches(password, user.getPassword())) {
			return true;
		}
		return false;
		
	}
}

