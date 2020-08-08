package com.vel.springdatajdbc.entities;

import java.util.List;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "application_id", "application_name", "accessDetails"})
//@Table("applications") 
public class Applications {

	//@Id
	//@Column("app_id")
	public String application_id;	
	//@Column("app_name")
	public String application_name;		
	List<Access> accessDetails;
	
	@JsonGetter("app_id")
	public String getApplication_id() {
		return application_id;
	}
	//@JsonSetter("app_id")
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	
	@JsonGetter("app_name")
	public String getApplication_name() {
		return application_name;
	}
	//@JsonSetter("app_name")
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	
	@JsonGetter("access")
	public List<Access> getAccessDetails() {
		return accessDetails;
	}
	public void setAccessDetails(List<Access> accessDetails) {
		this.accessDetails = accessDetails;
	}
	
	@Override
	public String toString() {
		return "Applications [application_id=" + application_id + ", application_name=" + application_name
				+ ", accessDetails=" + accessDetails + "]";
	}	
	
}
