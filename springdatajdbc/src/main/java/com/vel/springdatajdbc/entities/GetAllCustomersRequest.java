package com.vel.springdatajdbc.entities;

public class GetAllCustomersRequest {

	public String cus_first_name;
	public String cus_last_name;
	public String cus_app_name;
	public String cus_app_access;
	
	public String getCus_first_name() {
		return cus_first_name;
	}
	public void setCus_first_name(String cus_first_name) {
		this.cus_first_name = cus_first_name;
	}
	public String getCus_last_name() {
		return cus_last_name;
	}
	public void setCus_last_name(String cus_last_name) {
		this.cus_last_name = cus_last_name;
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
