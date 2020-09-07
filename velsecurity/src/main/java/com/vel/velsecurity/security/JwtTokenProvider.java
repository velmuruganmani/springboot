package com.vel.velsecurity.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.vel.velsecurity.entities.Customers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
//import com.vel.velsecurity.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import static com.vel.velsecurity.security.SecurityConstants.EXPIRATION_TIME;
import static com.vel.velsecurity.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {

	public String generateToken(Authentication authentication){
		/*
		User user = (User)authentication.getPrincipal();
		String userId = Long.toString(user.getId());	
		Date now = new Date(System.currentTimeMillis());		
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);      
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", user.getUsername());
        claims.put("name", user.getName());
        */
        
		
		Customers customer = (Customers)authentication.getPrincipal();
		String userId = Long.toString(customer.getId());	
		Date now = new Date(System.currentTimeMillis());		
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);      
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("customerloginid", customer.getCustomerloginid());
        claims.put("customername", customer.getCustomername());
		
		
		return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
		
	}

	//Validate the token
	public boolean validateToken(String token) {
	    try{
	        Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
	        return true;
	    }catch (SignatureException ex){
	    	//throw new SignatureException("SignatureException: "+ex);
	    }catch (MalformedJwtException ex){
	    	//throw new MalformedJwtException("SignatureException: "+ex);
	    }catch (ExpiredJwtException ex){
	    	//throw new ExpiredJwtException(null, null, "SignatureException: "+ex);
	    }catch (UnsupportedJwtException ex){
	    	//throw new UnsupportedJwtException("SignatureException: "+ex);
	    }catch (IllegalArgumentException ex){
	    	//throw new IllegalArgumentException("SignatureException: "+ex);
	    }
	    return false;
	}

	//Get login Id from token
	public String getUserIdFromJWT(String token){
	    Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	    String userLoginId = (String)claims.get("customerloginid");
	    return userLoginId;
	}

	}