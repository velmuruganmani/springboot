package com.vel.springdatajdbc.entities;

public class GetAllCustomersRequest {

	public String cus_name;
	public String cus_app_name;
	public String cus_app_access;
	
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_app_name() {
		return cus_app_name;
	}
	public void setCus_app_name(String cus_app_name) {
		this.cus_app_name = cus_app_name;
	}
	public String getCus_app_access() {
		return cus_app_access;
	}
	public void setCus_app_access(String cus_app_access) {
		this.cus_app_access = cus_app_access;
	}
	

	
	
}
