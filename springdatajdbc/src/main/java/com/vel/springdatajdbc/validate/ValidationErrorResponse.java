package com.vel.springdatajdbc.validate;

import java.util.Date;

/**
 * @author Brindha
 * Created for validation
 */

import java.util.List;

public class ValidationErrorResponse{

	private Date timestamp;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	private String errorMessage;
	private List<String> requestDescription;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<String> getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(List<String> requestDescription) {
		this.requestDescription = requestDescription;
	}
	public ValidationErrorResponse(Date timestamp, String errorMessage, List<String> requestDescription) {
		super();
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
		this.requestDescription = requestDescription;
	}
	
}
