package com.vel.springdatajdbc.entities;

public class Access {

	public String access_id;
	public String access_name;
	
	public String getAccess_id() {
		return access_id;
	}
	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}
	public String getAccess_name() {
		return access_name;
	}
	public void setAccess_name(String access_name) {
		this.access_name = access_name;
	}
	
	@Override
	public String toString() {
		return "Access [access_id=" + access_id + ", access_name=" + access_name + "]";
	}
	
	
}
