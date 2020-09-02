package com.vel.springdatajdbc.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Collections;
import com.vel.springdatajdbc.entities.Customers;
import com.vel.springdatajdbc.service.impl.CustomUserDetailsService;
import static com.vel.springdatajdbc.security.SecurityConstants.HEADER_STRING;
import static com.vel.springdatajdbc.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {

            String jwt = getJWTFromRequest(request);

            if(StringUtils.hasText(jwt)&& tokenProvider.validateToken(jwt)){
                String userLoginId = tokenProvider.getUserIdFromJWT(jwt);
                Customers userDetails = (Customers) customUserDetailsService.loadUserByUsername(userLoginId);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Collections.emptyList());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }catch (Exception ex){
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
		
	
	}


private String getJWTFromRequest(HttpServletRequest request){
    String bearerToken = request.getHeader(HEADER_STRING);

    if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
        return bearerToken.substring(7, bearerToken.length());
    }

    return null;
}

}
