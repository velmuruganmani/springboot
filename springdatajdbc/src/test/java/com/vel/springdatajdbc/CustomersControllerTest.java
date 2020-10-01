
package com.vel.springdatajdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vel.springdatajdbc.controller.CustomersController;
import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.ApplicationAccess;
import com.vel.springdatajdbc.entities.Applications;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;
import com.vel.springdatajdbc.repository.CustomersRepository;
import com.vel.springdatajdbc.service.CustomersService;
import com.vel.springdatajdbc.service.impl.CustomersServiceImpl;

import cucumber.api.java.Before;

@ExtendWith(MockitoExtension.class)
public class CustomersControllerTest {

	@Mock
	private CustomersRepository customerRepo;
	
	@InjectMocks
	private CustomersController customersController;

	@Mock
	private CustomersService customersService;

	@Mock
	private AddCustomersRequest addCustomersRequest;

	@Mock
	private ApplicationAccess applicationAccessDetails;

	private static ObjectMapper mapper = new ObjectMapper();

	private final String URL = "/addCustomers";

	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPostExample() throws Exception {

		AddCustomersRequest addCustomersRequest = new AddCustomersRequest();
		ApplicationAccess applicationAccessDetails = new ApplicationAccess();
		List<ApplicationAccess> applicationaccesses = new ArrayList<ApplicationAccess>();
		GetAllCustomersResponse getAllCustomersResponse = new GetAllCustomersResponse();
		Customers customers = new Customers();
		Applications application = new Applications();
		List<Applications> applicationsList = new ArrayList<Applications>();

		applicationAccessDetails.setApp_access_name("Admin");
		applicationAccessDetails.setApp_module_name("Global Profile");
		applicationAccessDetails.setApp_name("Customers Application 1");
		applicationaccesses.add(applicationAccessDetails);
		addCustomersRequest.setCus_login("gouthamb24");
		addCustomersRequest.setCus_first_name("Brindha");
		addCustomersRequest.setCus_last_name("Goutham");
		addCustomersRequest.setCus_telephone("9677443203");
		addCustomersRequest.setCus_email("brindha.premkumar@tcs.com");
		addCustomersRequest.setCus_status("0");
		addCustomersRequest.setCus_iso_code("EUR");
		addCustomersRequest.setCus_fax("12344");
		addCustomersRequest.setCus_language("EUR");
		addCustomersRequest.setCus_serial_no("0");
		addCustomersRequest.setApplicationAccessDetails(applicationaccesses);

		String json = mapper.writeValueAsString(addCustomersRequest);

		application.setAccessDetails(null);
		application.setApplication_id("1");
		application.setApplication_name("Customers Application 1");
		applicationsList.add(application);

		customers.setCustomer_id("131");
		customers.setCustomer_login("gouthamb24");
		customers.setCustomer_first_name("Brindha");
		customers.setCustomer_last_name("Goutham");
		customers.setCustomer_telephone("9677443203");
		customers.setCustomer_email("brindha.premkumar@tcs.com");
		customers.setCustomer_status("0");
		customers.setCustomer_iso_code("EUR");
		customers.setCustomer_fax("12344");
		customers.setCustomer_language("EUR");
		customers.setCustomer_serial_no("0");
		customers.setApplicationsDetails(applicationsList);

		getAllCustomersResponse.setSuccessMessage("Customer Login ID created successfully");
		getAllCustomersResponse.setErrorMessage(null);
		getAllCustomersResponse.setCustomers(customers);

		when(customersService.addCustomers(addCustomersRequest)).thenReturn(getAllCustomersResponse);
		ResponseEntity responseEntity = customersController.addCustomers(addCustomersRequest);
		
		assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue(), "Incorrect reponse status");

	}


	@Test
	void testGetCustomersById() throws Exception {

		List<Customers> customers = new ArrayList<Customers>();
		
		  Customers customer = new Customers();
		  customer.setCustomer_login("gouthamb24");
		  customer.setCustomer_first_name("Brin");
		  customer.setCustomer_last_name("Goutham");
		  
		  customer.setCustomer_telephone("9677443203");
		  customer.setCustomer_email("brindha.premkumar@tcs.com");
		  customer.setCustomer_status("0"); customer.setCustomer_iso_code("EUR");
		  customer.setCustomer_fax("12344"); customer.setCustomer_language("EUR");
		  customer.setCustomer_serial_no("0");
		  
		  customers.add(customer);
		 

		when(customersService.getCustomersById(anyString())).thenReturn(customers);
		ResponseEntity responseEntity = customersController.getCustomersById("gouthamb24");
		List<Customers> customerResponse =  (List<Customers>) responseEntity.getBody();
		assertEquals("Brin", customerResponse.get(0).getCustomer_first_name());

		// when(customersRepository.getCustomersById(anyString())).thenReturn(customers);
	}

	@Test
	void testdeleteCustomers() throws Exception {
		
		GetAllCustomersResponse getAllCustomersResponse = new GetAllCustomersResponse();
		Customers customers = new Customers();
		Applications application = new Applications();
		List<Applications> applicationsList = new ArrayList<Applications>();
		application.setAccessDetails(null);
		application.setApplication_id("1");
		application.setApplication_name("Customers Application 1");
		applicationsList.add(application);

		customers.setCustomer_id("131");
		customers.setCustomer_login("gouthamb24");
		customers.setCustomer_first_name("Brindha");
		customers.setCustomer_last_name("Goutham");
		customers.setCustomer_telephone("9677443203");
		customers.setCustomer_email("brindha.premkumar@tcs.com");
		customers.setCustomer_status("0");
		customers.setCustomer_iso_code("EUR");
		customers.setCustomer_fax("12344");
		customers.setCustomer_language("EUR");
		customers.setCustomer_serial_no("0");
		customers.setApplicationsDetails(applicationsList);

		getAllCustomersResponse.setSuccessMessage("Customer details are deleted successfully");
		getAllCustomersResponse.setErrorMessage(null);
		getAllCustomersResponse.setCustomers(customers);
		
		when(customersService.deleteCustomers(anyString())).thenReturn(getAllCustomersResponse);
		ResponseEntity responseEntity = customersController.deleteCustomers("gouthamb24");
		GetAllCustomersResponse customerResponse =  (GetAllCustomersResponse) responseEntity.getBody();
		assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
	}

		

}
