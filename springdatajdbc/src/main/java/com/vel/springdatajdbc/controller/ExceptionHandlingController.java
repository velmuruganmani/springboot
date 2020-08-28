package com.vel.springdatajdbc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vel.springdatajdbc.validate.UserNotFoundException;
import com.vel.springdatajdbc.validate.ValidationErrorResponse;

@RestControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
		 @Override
		    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		        List<String> details = new ArrayList<>();
		        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
		            details.add(error.getDefaultMessage());
		        }
		        ValidationErrorResponse error = new ValidationErrorResponse(new Date(), "Add user",details);
		        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		    }
		 
		 @ExceptionHandler(UserNotFoundException.class)
		  public final ResponseEntity<ValidationErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
			 List<String> details = new ArrayList<>();
		        details.add(ex.getLocalizedMessage());
		        ValidationErrorResponse error = new ValidationErrorResponse(new Date(),"User Not Found", details);
		        return new ResponseEntity(error, HttpStatus.NOT_FOUND); 
		  }
}