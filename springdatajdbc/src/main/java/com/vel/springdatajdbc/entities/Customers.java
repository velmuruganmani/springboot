package com.vel.springdatajdbc.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import com.fasterxml.jackson.annotation.JsonSetter;


//@Table("customers")
@JsonPropertyOrder({ "customer_login", "customer_id", "customer_first_name", "customer_last_name",
	"customer_telephone","customer_email","customer_status","customer_iso_code","customer_fax",
	"customer_language","customer_serial_no", "applications"})
public class Customers implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
	//@Column("cus_id")
	public String customer_id;
	
	//@Column("cus_login")
	public String customer_login;
	
	//@Column("cus_first_name")
	public String customer_first_name;
	
	//@Column("cus_last_name")
	public String customer_last_name;
	
	//@Column("cus_telephone")
	public String customer_telephone;	
	
	//@Column("cus_email")
	public String customer_email;
	
	//@Column("cus_status")
	public String customer_status;
	
	//@Column("cus_iso_code")
	public String customer_iso_code;
	
	//@Column("cus_fax")
	public String customer_fax;
	
	//@Column("cus_language")
	public String customer_language;
	
	//@Column("cus_serial_no")
	public String customer_serial_no;
	
	public List<Applications> applicationsDetails;
	
	public String username;
	public String password;
	
	@JsonGetter("cus_id")
	public String getCustomer_id() {
		return customer_id;
	}
	//@JsonSetter("cus_id")
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	@JsonGetter("cus_login")
	public String getCustomer_login() {
		return customer_login;
	}
	//@JsonSetter("cus_login")
	public void setCustomer_login(String customer_login) {
		this.customer_login = customer_login;
	}
	
	@JsonGetter("cus_first_name")
	public String getCustomer_first_name() {
		return customer_first_name;
	}
	//@JsonSetter("cus_first_name")
	public void setCustomer_first_name(String customer_first_name) {
		this.customer_first_name = customer_first_name;
	}
	
	@JsonGetter("cus_last_name")
	public String getCustomer_last_name() {
		return customer_last_name;
	}
	//@JsonSetter("cus_last_name")
	public void setCustomer_last_name(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}
	
	@JsonGetter("cus_telephone")
	public String getCustomer_telephone() {
		return customer_telephone;
	}
	//@JsonSetter("cus_telephone")
	public void setCustomer_telephone(String customer_telephone) {
		this.customer_telephone = customer_telephone;
	}
	
	@JsonGetter("cus_email")
	public String getCustomer_email() {
		return customer_email;
	}
	//@JsonSetter("cus_email")
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	@JsonGetter("cus_status")
	public String getCustomer_status() {
		return customer_status;
	}
	//@JsonSetter("cus_status")
	public void setCustomer_status(String customer_status) {
		this.customer_status = customer_status;
	}
	
	@JsonGetter("cus_iso_code")
	public String getCustomer_iso_code() {
		return customer_iso_code;
	}
	//@JsonSetter("cus_iso_code")
	public void setCustomer_iso_code(String customer_iso_code) {
		this.customer_iso_code = customer_iso_code;
	}
	
	@JsonGetter("cus_fax")
	public String getCustomer_fax() {
		return customer_fax;
	}
	//@JsonSetter("cus_fax")
	public void setCustomer_fax(String customer_fax) {
		this.customer_fax = customer_fax;
	}
	
	@JsonGetter("cus_language")
	public String getCustomer_language() {
		return customer_language;
	}
	//@JsonSetter("cus_language")
	public void setCustomer_language(String customer_language) {
		this.customer_language = customer_language;
	}
	
	@JsonGetter("cus_serial_no")
	public String getCustomer_serial_no() {
		return customer_serial_no;
	}
	//@JsonSetter("cus_serial_no")
	public void setCustomer_serial_no(String customer_serial_no) {
		this.customer_serial_no = customer_serial_no;
	}
	
	@JsonGetter("applications")
	public List<Applications> getApplicationsDetails() {
		return applicationsDetails;
	}
	public void setApplicationsDetails(List<Applications> applicationsDetails) {
		this.applicationsDetails = applicationsDetails;
	}

	@JsonIgnore
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Customers [customer_id=" + customer_id + ", customer_login=" + customer_login + ", customer_first_name="
				+ customer_first_name + ", customer_last_name=" + customer_last_name + ", customer_telephone="
				+ customer_telephone + ", customer_email=" + customer_email + ", customer_status=" + customer_status
				+ ", customer_iso_code=" + customer_iso_code + ", customer_fax=" + customer_fax + ", customer_language="
				+ customer_language + ", customer_serial_no=" + customer_serial_no + ", applicationsDetails="
				+ applicationsDetails + ", username=" + username + ", password=" + password + "]";
	}
	
	/* UserDetails interface methods  - Start */
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	/* UserDetails interface methods  - End */
	
	
		
}
