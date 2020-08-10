package com.vel.springdatajdbc.service;

import java.util.List;
import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;

public interface CustomersService {

	List<Customers> getCustomersById(String loginId);
	List<Customers> getAllCustomers(GetAllCustomersRequest request);
	GetAllCustomersResponse addCustomers(AddCustomersRequest addCustomersRequest);
	GetAllCustomersResponse editCustomers(AddCustomersRequest editCustomersRequest);
}
