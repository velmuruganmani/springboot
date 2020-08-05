package com.vel.springdatajdbc.service;

import java.util.List;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;

public interface CustomersService {

	List<Customers> getCustomersById(String loginId);
	List<Customers> getAllCustomers(GetAllCustomersRequest request);
}
