package com.vel.springdatajdbc.entities;

import java.util.List;


public class Applications {

	public String app_id;
	public String app_name;
	List<Access> accessDetails;
	
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public List<Access> getAccessDetails() {
		return accessDetails;
	}
	public void setAccessDetails(List<Access> accessDetails) {
		this.accessDetails = accessDetails;
	}
	
	@Override
	public String toString() {
		return "Applications [app_id=" + app_id + ", app_name=" + app_name + ", accessDetails=" + accessDetails + "]";
	}
	
	
}
