package com.vel.springdatajdbc.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;
import com.vel.springdatajdbc.service.CustomersService;
import com.vel.springdatajdbc.validate.UserNotFoundException;
import com.vel.springdatajdbc.validate.ValidationErrorResponse;


@RestController
@RequestMapping("/customers")
public class CustomersController {
	
	@Autowired
	CustomersService customersService;
	
	@GetMapping("/home")
	public ResponseEntity<?> home() {	
		return new ResponseEntity<>("Hai", HttpStatus.OK);
	}

	@GetMapping("/get/{loginId}")
    public ResponseEntity<?> getCustomersById(@PathVariable String loginId) throws Exception{
		
        List<Customers> user = customersService.getCustomersById(loginId);
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@GetMapping("/get/search")
    public ResponseEntity<?> getAllCustomers(@RequestBody GetAllCustomersRequest getAllCustomersRequest) throws Exception{
		
        List<Customers> user = customersService.getAllCustomers(getAllCustomersRequest);
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	//Added @Valid to validate AddCustomerRequest 
	@PostMapping("/post/addUser")
    public ResponseEntity<?> addCustomers(@RequestBody @Valid AddCustomersRequest addCustomersRequest) throws Exception{
		
		GetAllCustomersResponse user = customersService.addCustomers(addCustomersRequest);
		
		if(user.getErrorMessage()!=null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
        
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
	
	@PostMapping("/post/editUser")
    public ResponseEntity<?> editCustomers(@RequestBody AddCustomersRequest editCustomersRequest) throws Exception{
		
		GetAllCustomersResponse user = customersService.editCustomers(editCustomersRequest);
		
		if(user.getErrorMessage()!=null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{loginId}")
    public ResponseEntity<?> deleteCustomers(@PathVariable String loginId) throws Exception{
		
		GetAllCustomersResponse user = customersService.deleteCustomers(loginId);
		 
		if(user.getErrorMessage()!=null) {
			throw new UserNotFoundException("Invalid customer id : " + loginId);
		}
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
