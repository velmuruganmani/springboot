package com.vel.springdatajdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vel.springdatajdbc.controller.CustomersController;
import com.vel.springdatajdbc.entities.AddCustomersRequest;
import com.vel.springdatajdbc.entities.ApplicationAccess;
import com.vel.springdatajdbc.entities.GetAllCustomersResponse;
import com.vel.springdatajdbc.service.impl.CustomersServiceImpl;

import cucumber.api.java.Before;
 
@WebMvcTest(CustomersController.class)
@AutoConfigureMockMvc
public class CustomersControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
  
    @MockBean
    private CustomersServiceImpl customersServiceImpl;
    
    @MockBean
    private AddCustomersRequest addCustomersRequest;
    
    @MockBean
    private ApplicationAccess applicationAccessDetails;
 
    private static ObjectMapper mapper = new ObjectMapper();
    
    private final String URL = "/addCustomers";
    
    @Autowired
    private WebApplicationContext context;
    
    @Before
    public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
  
	
    @Test public void testPostExample() throws Exception { AddCustomersRequest
    	addCustomersRequest = new AddCustomersRequest(); ApplicationAccess
    	applicationAccessDetails = new ApplicationAccess(); List<ApplicationAccess>
    	applicationaccesses = new ArrayList<ApplicationAccess>();
    	GetAllCustomersResponse getAllCustomersResponse = new
    			GetAllCustomersResponse();

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
    	addCustomersRequest.setApplicationAccessDetails(applicationaccesses); String
    	json = mapper.writeValueAsString(addCustomersRequest);

    	Mockito.when(customersServiceImpl.addCustomers(addCustomersRequest)).thenReturn(
    			getAllCustomersResponse);
    	MvcResult result =  mockMvc.perform(post("/customers/post/addUser").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
    			.content(json).characterEncoding("utf-8")).andReturn(); 
    	int status = result.getResponse().getStatus(); //verify the service method was called
    	//verify(customersService).addCustomers(any(AddCustomersRequest.class));
    	assertEquals(HttpStatus.CREATED.value(), status, "Incorrect reponse status");


    }
	 
    
    
    
    @Test
    public void testDeleteExample() throws Exception {
       	GetAllCustomersResponse getAllCustomersResponse = new GetAllCustomersResponse();
    	Mockito.when(customersServiceImpl.deleteCustomers("brindha6")).thenReturn(getAllCustomersResponse);
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/customers/delete/brindha6").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
    			.characterEncoding("utf-8")).andReturn();
    	int status = result.getResponse().getStatus();
    	//verify the service method was called
    	//verify(customersService).addCustomers(any(AddCustomersRequest.class));
    	assertEquals(HttpStatus.OK.value(), status, "Incorrect reponse status");
    	
    	
    }

}


