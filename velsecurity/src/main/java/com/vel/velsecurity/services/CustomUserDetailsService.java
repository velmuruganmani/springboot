package com.vel.velsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vel.velsecurity.entities.Customers;
//import org.springframework.transaction.annotation.Transactional;
//import com.vel.velsecurity.entities.User;
import com.vel.velsecurity.repositories.CustomersRepository;
//import com.vel.velsecurity.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	/*@Autowired
    private UserRepository userRepository;*/
	
	@Autowired
    private CustomersRepository customersRepository;

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user==null) new UsernameNotFoundException("User not found");
		
		return user;
	}*/
	/*
	@Transactional
	public User loadUserById (Long id){
        User user = userRepository.getById(id);
        if(user==null) new UsernameNotFoundException("User not found");
        return user;

    }*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customers customer = customersRepository.findByCustomerloginid(username);
		if(customer==null) new UsernameNotFoundException("User not found");
		customer.setUsername(customer.getCustomerloginid());
		//customer.setPassword(customer.getCustomerpassword());
		//myaxalmpassword
		customer.setPassword("$2a$10$JlyeWI467P8wIZdCQc0NRecK5/aRyT1KJ/UTbl29ZrDlL3v7rCGFu");
		return customer;
	}

	
}
