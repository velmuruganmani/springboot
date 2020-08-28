package com.vel.springdatajdbc.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vel.springdatajdbc.service.CustomersService;

public class UserExistsValidator implements ConstraintValidator<UserExistsCheck, String> {

	@Autowired
	CustomersService customersService;
	
	@Override
	public boolean isValid(String userId, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return userId!=null && !customersService.checkUserExists(userId);
	}

}
