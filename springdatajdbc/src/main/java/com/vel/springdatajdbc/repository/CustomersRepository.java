package com.vel.springdatajdbc.repository;

import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.List;
//import org.springframework.dao.DataAccessException;
import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;

public interface CustomersRepository {

	List<Customers> getCustomersById(String loginId);
	List<Customers> getAllCustomers(GetAllCustomersRequest request);
	GetAllCustomersResponse addCustomers(AddCustomersRequest addCustomersRequest) throws SQLException;
	//Customers mapRow(ResultSet rs, int rowNum) throws SQLException;
	//List<Customers> extractData(ResultSet rs) throws SQLException, DataAccessException;
	GetAllCustomersResponse editCustomers(AddCustomersRequest editCustomersRequest) throws SQLException;
	GetAllCustomersResponse deleteCustomers(String loginId);
}
