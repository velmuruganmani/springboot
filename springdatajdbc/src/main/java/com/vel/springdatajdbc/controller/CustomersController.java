package com.vel.springdatajdbc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersRequest;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;
import com.vel.springdatajdbc.service.CustomersService;
import com.vel.springdatajdbc.service.impl.MapValidationErrorService;


@RestController
@RequestMapping("/api/users/customers")
public class CustomersController {
	
	@Autowired
	CustomersService customersService;
	
	 @Autowired
	 private MapValidationErrorService mapValidationErrorService;
	
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
	
	@PostMapping("/post/addUser")
    public ResponseEntity<?> addCustomers(@RequestBody AddCustomersRequest addCustomersRequest) throws Exception{
		
		GetAllCustomersResponse user = customersService.addCustomers(addCustomersRequest);
		
		if(user.getErrorMessage()!=null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
        
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
	
	@PostMapping("/post/editUser")
    public ResponseEntity<?> editCustomers(@Valid @RequestBody AddCustomersRequest editCustomersRequest, BindingResult result) throws Exception{
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
		
		GetAllCustomersResponse user = customersService.editCustomers(editCustomersRequest);
		
		if(user.getErrorMessage()!=null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{loginId}")
    public ResponseEntity<?> deleteCustomers(@PathVariable String loginId) throws Exception{
		
		GetAllCustomersResponse user = customersService.deleteCustomers(loginId);
		        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
