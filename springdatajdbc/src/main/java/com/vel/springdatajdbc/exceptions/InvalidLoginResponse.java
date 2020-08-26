package com.vel.springdatajdbc.exceptions;

public class InvalidLoginResponse {

	private String userId;
	private String userLoginId;
	
	public InvalidLoginResponse() {
        this.userId = "Invalid User ID";
        this.userLoginId = "Invalid Login ID";
    }
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	
	
	
}
