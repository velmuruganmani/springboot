package com.vel.springdatajdbc.entities;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.vel.springdatajdbc.validate.UserExistsCheck;

public class AddCustomersRequest {

	//Annotations added for validation 
	public String cus_id;
	@NotBlank(message="Customer Login Id cannot be empty")
	@Pattern(regexp = "^[a-zA-Z0-9]*$" , message = "Invalid format")
	@UserExistsCheck
	public String cus_login;
	@NotBlank(message="First Name cannot be empty")
	@Pattern(regexp = "^[a-zA-Z\\\\s']*$")
	public String cus_first_name;	
	@NotBlank(message="Last Name cannot be empty")
	@Pattern(regexp = "^[a-zA-Z\\\\s']*$", message = "Invalid input for Name")
	public String cus_last_name;
	@NotBlank(message="Telephone cannot be empty")
	public String cus_telephone;
	@Email(message="Please provide a valid email address") 
	@NotBlank(message="Please provide the email address")
	public String cus_email;
	public String cus_status;
	public String cus_iso_code;
	public String cus_fax;
	public String cus_language;
	public String cus_serial_no;
	public List<ApplicationAccess> applicationAccessDetails;
	
	public String getCus_id() {
		return cus_id;
	}
	public void setCus_id(String cus_id) {
		this.cus_id = cus_id;
	}
	public String getCus_login() {
		return cus_login;
	}
	public void setCus_login(String cus_login) {
		this.cus_login = cus_login;
	}
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
	public String getCus_telephone() {
		return cus_telephone;
	}
	public void setCus_telephone(String cus_telephone) {
		this.cus_telephone = cus_telephone;
	}
	public String getCus_email() {
		return cus_email;
	}
	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}
	public String getCus_status() {
		return cus_status;
	}
	public void setCus_status(String cus_status) {
		this.cus_status = cus_status;
	}
	public String getCus_iso_code() {
		return cus_iso_code;
	}
	public void setCus_iso_code(String cus_iso_code) {
		this.cus_iso_code = cus_iso_code;
	}
	public String getCus_fax() {
		return cus_fax;
	}
	public void setCus_fax(String cus_fax) {
		this.cus_fax = cus_fax;
	}
	public String getCus_language() {
		return cus_language;
	}
	public void setCus_language(String cus_language) {
		this.cus_language = cus_language;
	}
	public String getCus_serial_no() {
		return cus_serial_no;
	}
	public void setCus_serial_no(String cus_serial_no) {
		this.cus_serial_no = cus_serial_no;
	}
	public List<ApplicationAccess> getApplicationAccessDetails() {
		return applicationAccessDetails;
	}
	public void setApplicationAccessDetails(List<ApplicationAccess> applicationAccessDetails) {
		this.applicationAccessDetails = applicationAccessDetails;
	}
	
	
}
