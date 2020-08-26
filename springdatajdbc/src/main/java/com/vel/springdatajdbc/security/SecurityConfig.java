package com.vel.springdatajdbc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.vel.springdatajdbc.service.impl.CustomUserDetailsService;
import static com.vel.springdatajdbc.security.SecurityConstants.SIGN_UP_URLS;
import static com.vel.springdatajdbc.security.SecurityConstants.H2_URL;
import org.springframework.security.config.BeanIds;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
       
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
		/*auth.inMemoryAuthentication()
        .withUser("user").password("{noop}password").roles("USER")
        .and()
        .withUser("admin").password("{noop}password").roles("ADMIN");*/
	}
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.cors()
         .and()
     .csrf()
         .disable()
     .exceptionHandling()
         .authenticationEntryPoint(unauthorizedHandler)
         .and()
     .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()         
     .authorizeRequests()
         .antMatchers("/",
             "/favicon.ico",
             "/**/*.png",
             "/**/*.gif",
             "/**/*.svg",
             "/**/*.jpg",
             "/**/*.html",
             "/**/*.css",
             "/**/*.js")
             .permitAll()
         .antMatchers("/api/auth/**")
             .permitAll()
         .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
             .permitAll()
         .antMatchers(HttpMethod.POST, "/api/users/**").access("ROLE_ADMIN")
         .anyRequest()
             .authenticated();	
	}
	
}
