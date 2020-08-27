package com.vel.velsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.vel.velsecurity.entities.User;
import com.vel.velsecurity.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser (User newUser){
		
	  newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
	  newUser.setUsername(newUser.getUsername());
	  //newUser.setConfirmPassword("");
	  
	  return userRepository.save(newUser);
	}
}
