package com.vel.velsecurity.controller;

import static com.vel.velsecurity.security.SecurityConstants.TOKEN_PREFIX;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vel.velsecurity.entities.Customers;
import com.vel.velsecurity.entities.request.LoginRequest;
import com.vel.velsecurity.entities.response.JWTLoginSucessReponse;
import com.vel.velsecurity.exceptions.InvalidLoginResponse;
import com.vel.velsecurity.security.JwtTokenProvider;
import com.vel.velsecurity.services.CustomersService;
import com.vel.velsecurity.services.MapValidationErrorService;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
		
	@Autowired
    private CustomersService customersService;
	
	@Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }
	
	@PostMapping("/register")
    public ResponseEntity<?> registerCustomers(@Valid @RequestBody Customers customers, BindingResult result){
		
		Customers newCustomer = customersService.saveCustomers(customers);
        return  new ResponseEntity<Customers>(newCustomer, HttpStatus.CREATED);
	
	}
	
	@PostMapping("/update")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Customers customers, BindingResult result, HttpServletResponse response){

		int status = 0;
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        status = response.getStatus();
		if(status==401) {
			HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
			InvalidLoginResponse loginResponse = new InvalidLoginResponse();
			return new ResponseEntity<>(loginResponse, httpStatus);
		}else {

        Customers updateUser = customersService.updateCustomers(customers);
        return new ResponseEntity<Customers>(updateUser, HttpStatus.CREATED);
		}
    }



}
