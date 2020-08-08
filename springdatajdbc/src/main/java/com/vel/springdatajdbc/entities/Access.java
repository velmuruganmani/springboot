package com.vel.springdatajdbc.entities;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.fasterxml.jackson.annotation.JsonSetter;

@JsonPropertyOrder({ "app_access_id", "app_access_name"})
//@Table("access")
public class Access {

	//@Id
	//@Column("access_id")
	public String app_access_id;
	
	//@Column("access_name")
	public String app_access_name;
	
	@JsonGetter("access_id")
	public String getApp_access_id() {
		return app_access_id;
	}
	//@JsonSetter("access_id")
	public void setApp_access_id(String app_access_id) {
		this.app_access_id = app_access_id;
	}
	
	@JsonGetter("access_name")
	public String getApp_access_name() {
		return app_access_name;
	}
	//@JsonSetter("access_name")
	public void setApp_access_name(String app_access_name) {
		this.app_access_name = app_access_name;
	}
	
	@Override
	public String toString() {
		return "Access [app_access_id=" + app_access_id + ", app_access_name=" + app_access_name + "]";
	}
	
	
	
}
