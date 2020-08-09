package com.vel.springdatajdbc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.vel.springdatajdbc.entities.Customers;

public class CustomerResultSetExtractor implements ResultSetExtractor<List<Customers>>  {

	@Override
	public List<Customers> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Customers> customersList = new ArrayList<Customers>();
		Customers customer = new Customers();
		while(rs.next()){			 
		customer.setCustomer_login(rs.getString("cus_login"));
		customer.setCustomer_id(rs.getString("cus_id"));
		customer.setCustomer_first_name(rs.getString("cus_first_name"));
		customer.setCustomer_last_name(rs.getString("cus_last_name"));
		customer.setCustomer_email(rs.getString("cus_email"));
		customer.setCustomer_fax(rs.getString("cus_fax"));
		customer.setCustomer_iso_code(rs.getString("cus_iso_code"));
		customer.setCustomer_language(rs.getString("cus_language"));
		customer.setCustomer_serial_no(rs.getString("cus_serial_no"));
		customer.setCustomer_status(rs.getString("cus_status"));
		customer.setCustomer_telephone(rs.getString("cus_telephone"));
		customersList.add(customer);
		}
		return customersList;
	}

}
