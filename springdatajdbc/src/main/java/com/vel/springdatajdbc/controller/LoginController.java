package com.vel.springdatajdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vel.springdatajdbc.entities.JWTLoginSucessReponse;
import com.vel.springdatajdbc.entities.LoginRequest;
import com.vel.springdatajdbc.security.JwtTokenProvider;
import com.vel.springdatajdbc.service.impl.MapValidationErrorService;
import static com.vel.springdatajdbc.security.SecurityConstants.TOKEN_PREFIX;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class LoginController {

	@Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    
    
	@GetMapping("/loginhome")
	public ResponseEntity<?> home() {	
		return new ResponseEntity<>("Hai", HttpStatus.OK);
	}
	
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){

    	ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
    	
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
	
}
