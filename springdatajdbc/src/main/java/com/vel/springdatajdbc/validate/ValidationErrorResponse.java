package com.vel.springdatajdbc.validate;

/**
 * @author Brindha
 * Created for validation
 */
import java.io.Serializable;

public class ValidationErrorResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String requestDescription;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	public ValidationErrorResponse(String errorMessage, String requestDescription) {
		super();
		this.errorMessage = errorMessage;
		this.requestDescription = requestDescription;
	}

}
