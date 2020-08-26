package com.vel.springdatajdbc.service;

import java.sql.SQLException;
import java.util.List;
import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;

public interface CustomersService {

	List<Customers> getCustomersById(String loginId);
	List<Customers> getAllCustomers(GetAllCustomersRequest request);
	GetAllCustomersResponse addCustomers(AddCustomersRequest addCustomersRequest) throws SQLException;
	GetAllCustomersResponse editCustomers(AddCustomersRequest editCustomersRequest) throws SQLException;
	GetAllCustomersResponse deleteCustomers(String loginId);
}
