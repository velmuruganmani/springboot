package com.vel.springdatajdbc.security;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.vel.springdatajdbc.entities.Customers;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;
import static com.vel.springdatajdbc.security.SecurityConstants.EXPIRATION_TIME;
import static com.vel.springdatajdbc.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {
	

public String generateToken(Authentication authentication){
		
		Customers user = (Customers)authentication.getPrincipal();
		String loginId = user.getCustomer_login();
		
		Date now = new Date(System.currentTimeMillis());		
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);
        
        
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", loginId);
        claims.put("userid", user.getCustomer_id());
        claims.put("useremailid", user.getCustomer_email());
		
		return Jwts.builder()
                .setSubject(loginId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
		
	}

//Validate the token
public boolean validateToken(String token, HttpServletRequest httpServletRequest) throws ServletException {
    try{
        Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        return true;
    }catch (SignatureException ex){
        System.out.println("Invalid JWT Signature");
    }catch (MalformedJwtException ex){
        System.out.println("Invalid JWT Token");
    }catch (ExpiredJwtException ex){
        System.out.println("Expired JWT token");
        //httpServletRequest.setAttribute("expired",ex.getMessage());     
        //throw new ServletException(ex.toString());
    }catch (UnsupportedJwtException ex){
        System.out.println("Unsupported JWT token");
    }catch (IllegalArgumentException ex){
        System.out.println("JWT claims string is empty");
    }
    return false;
}

//Get login Id from token
public String getUserIdFromJWT(String token){
    Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    String loginid = (String)claims.get("id");
    return loginid;
}

}
