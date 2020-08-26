package com.vel.springdatajdbc.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;
import com.vel.springdatajdbc.repository.CustomersRepository;
import com.vel.springdatajdbc.service.CustomersService;

@Service
public class CustomersServiceImpl implements CustomersService {
	
	@Autowired
	CustomersRepository customersRepository;

	@Override
	public List<Customers> getCustomersById(String loginId) {
		List<Customers> user = customersRepository.getCustomersById(loginId);
		return user;
	}

	@Override
	public List<Customers> getAllCustomers(GetAllCustomersRequest request) {
		List<Customers> user = customersRepository.getAllCustomers(request);
		return user;
	}

	@Override
	//@Transactional( propagation = Propagation.REQUIRED, rollbackFor =  {Exception.class, RuntimeException.class,})
	public GetAllCustomersResponse addCustomers(AddCustomersRequest addCustomersRequest) throws SQLException{
		GetAllCustomersResponse user;
		user = customersRepository.addCustomers(addCustomersRequest);
		return user;
	}

	@Override
	public GetAllCustomersResponse editCustomers(AddCustomersRequest editCustomersRequest) throws SQLException {
		GetAllCustomersResponse user = customersRepository.editCustomers(editCustomersRequest);
		return user;
	}

	@Override
	public GetAllCustomersResponse deleteCustomers(String loginId) {
		GetAllCustomersResponse user = customersRepository.deleteCustomers(loginId);
		return user;
	}

}
