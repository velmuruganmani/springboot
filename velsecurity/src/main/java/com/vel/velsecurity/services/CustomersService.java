package com.vel.velsecurity.services;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.vel.velsecurity.entities.Customers;
import com.vel.velsecurity.repositories.CustomersRepository;

@Service
public class CustomersService {
	
	@Autowired
	private CustomersRepository customersRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Customers saveCustomers (Customers newCustomers){
		
		newCustomers.setCustomerpassword(bCryptPasswordEncoder.encode(newCustomers.getCustomerpassword()));
		newCustomers.setCustomerloginid(newCustomers.getCustomerloginid());
	  
	  return customersRepository.save(newCustomers);
	}

	public Customers updateCustomers(@Valid Customers updateCustomers) {
		Customers findCustomers = customersRepository.findByCustomerloginid(updateCustomers.getCustomerloginid());
		if(findCustomers!=null) {
			findCustomers.setCustomername(updateCustomers.getCustomername());
			customersRepository.save(findCustomers);
		}
		return findCustomers;
	}

}
