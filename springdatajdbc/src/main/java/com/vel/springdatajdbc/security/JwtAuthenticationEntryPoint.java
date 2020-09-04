package com.vel.springdatajdbc.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.vel.springdatajdbc.exceptions.InvalidLoginResponse;
//import com.vel.springdatajdbc.exceptions.JwtException;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException{
		
		/*final String expired = (String) httpServletRequest.getAttribute("expired");
        if (expired!=null){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,expired);
        }else{
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Login details");
        }*/
		
		InvalidLoginResponse loginResponse = new InvalidLoginResponse();
		String jsonLoginResponse = new Gson().toJson(loginResponse);
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setStatus(401);
		httpServletResponse.getWriter().print(jsonLoginResponse);
		
	}

	
}
