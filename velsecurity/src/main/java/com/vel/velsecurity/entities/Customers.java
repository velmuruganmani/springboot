package com.vel.velsecurity.entities;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "springbootsecurity.customers")
public class Customers implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cus_id", nullable = false, updatable = false)
    private Long id;

	@NotBlank(message = "loginid is required")
	@Column(unique = true, name = "cus_login_id")
	private String customerloginid;
	
    @NotBlank(message = "username is required")
	@Column(name = "cus_name")
    private String customername;
    
    @NotBlank(message = "Password is required")
	@Column(name = "cus_password")
    private String customerpassword;
    
    @Transient
    private String username;
    
    @Transient
    private String password;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 public String getCustomerloginid() {
		return customerloginid;
	}

	public void setCustomerloginid(String customerloginid) {
		this.customerloginid = customerloginid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomerpassword() {
		return customerpassword;
	}

	public void setCustomerpassword(String customerpassword) {
		this.customerpassword = customerpassword;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
