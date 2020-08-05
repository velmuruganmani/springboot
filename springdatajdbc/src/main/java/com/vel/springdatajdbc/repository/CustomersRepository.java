package com.vel.springdatajdbc.repository;

import java.util.List;

import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;

public interface CustomersRepository {

	List<Customers> getCustomersById(String loginId);

	List<Customers> getAllCustomers(GetAllCustomersRequest request);
}
