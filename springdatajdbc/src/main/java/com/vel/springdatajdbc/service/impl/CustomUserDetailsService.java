package com.vel.springdatajdbc.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.repository.CustomersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	CustomersRepository customersRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {		
		List<Customers> customersList = new ArrayList<Customers>();
		customersList= customersRepository.getCustomersById(loginId);
		Customers customers = customersList.get(0);
		if(customers==null) new UsernameNotFoundException("User not found");
		customers.setUsername("username");
		customers.setPassword("password");
		return customers;
	}

}
