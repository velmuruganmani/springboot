package com.vel.springdatajdbc.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.vel.springdatajdbc.entities.Customers;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static com.vel.springdatajdbc.security.SecurityConstants.EXPIRATION_TIME;
import static com.vel.springdatajdbc.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {

public String generateToken(Authentication authentication){
		
		Customers user = (Customers)authentication.getPrincipal();
		String userId = user.getCustomer_login();
		
		Date now = new Date(System.currentTimeMillis());		
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);
        
        
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", user.getUsername());
		
		return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
		
	}
}
