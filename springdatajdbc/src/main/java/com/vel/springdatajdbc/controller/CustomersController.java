package com.vel.springdatajdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.service.CustomersService;


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
    public ResponseEntity<?> getProjectById(@PathVariable String loginId) throws Exception{
		
        List<Customers> user = customersService.getCustomersById(loginId);
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
