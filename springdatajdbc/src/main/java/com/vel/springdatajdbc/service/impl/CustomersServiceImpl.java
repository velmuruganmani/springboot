package com.vel.springdatajdbc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.repository.CustomersRepository;
import com.vel.springdatajdbc.service.CustomersService;

@Service
public class CustomersServiceImpl implements CustomersService {
	
	@Autowired
	CustomersRepository customersRepository;

	@Override
	public List<Customers> getCustomersById(String loginId) {
		// TODO Auto-generated method stub
		List<Customers> user = customersRepository.getCustomersById(loginId);
		return user;
	}

}
