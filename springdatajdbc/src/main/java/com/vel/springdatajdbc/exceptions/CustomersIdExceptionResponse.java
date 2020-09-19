package com.vel.springdatajdbc.exceptions;

public class CustomersIdExceptionResponse {

	private String customersId;
	
	public CustomersIdExceptionResponse(String customersId) {
        this.customersId = customersId;
    }

	public String getCustomersId() {
		return customersId;
	}

	public void setCustomersId(String customersId) {
		this.customersId = customersId;
	}
	
	
}
