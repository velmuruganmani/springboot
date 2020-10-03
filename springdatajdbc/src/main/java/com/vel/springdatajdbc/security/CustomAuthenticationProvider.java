package com.vel.springdatajdbc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.vel.springdatajdbc.entities.Customers;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	List<Customers> customersList = new ArrayList<Customers>();
	
	public CustomAuthenticationProvider() {
		Customers customers = new Customers();
		customers.setUsername("vel");
		customers.setPassword("vel");
		customers.setRole("ROLE_ADMIN");
		customersList.add(customers);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<Customers> authenticatedUser = customersList.stream().filter(
                user -> user.getUsername().equals(name) && user.getPassword().equals(password)
        ).findFirst();

        if(!authenticatedUser.isPresent()){
            throw new BadCredentialsException("Some Text");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authenticatedUser.get().getRole()));
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
        return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
