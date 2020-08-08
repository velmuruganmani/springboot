package com.vel.springdatajdbc.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "application_config_id", "	public", "application_config_name"})
public class ApplicationsConfig {
	
	public String application_config_id;	
	public String application_config_name;
	public String application_id;
	
	@JsonGetter("app_config_id")
	public String getApplication_config_id() {
		return application_config_id;
	}
	public void setApplication_config_id(String application_config_id) {
		this.application_config_id = application_config_id;
	}
	
	@JsonGetter("app_config_name")
	public String getApplication_config_name() {
		return application_config_name;
	}
	public void setApplication_config_name(String application_config_name) {
		this.application_config_name = application_config_name;
	}
	
	@JsonGetter("app_id")
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	
	
	
	
	
	
}
