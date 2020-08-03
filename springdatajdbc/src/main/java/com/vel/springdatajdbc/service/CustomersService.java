package com.vel.springdatajdbc.service;

import java.util.List;

import com.vel.springdatajdbc.entities.Customers;

public interface CustomersService {

	List<Customers> getCustomersById(String loginId);
}
