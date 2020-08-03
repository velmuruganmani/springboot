package com.vel.springdatajdbc.repository;

import java.util.List;

import com.vel.springdatajdbc.entities.Customers;

public interface CustomersRepository {

	List<Customers> getCustomersById(String loginId);
}
