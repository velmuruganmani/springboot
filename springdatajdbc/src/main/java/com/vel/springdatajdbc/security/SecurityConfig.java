package com.vel.springdatajdbc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.vel.springdatajdbc.service.impl.CustomUserDetailsService;
import static com.vel.springdatajdbc.security.SecurityConstants.SIGN_UP_URLS;
import static com.vel.springdatajdbc.security.SecurityConstants.H2_URL;
import org.springframework.security.config.BeanIds;
//import static com.vel.springdatajdbc.security.SecurityConstants.GET_URLS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
	
	@Configuration
    @Order(1)
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                        .anyRequest().hasAnyRole("ADMIN", "API")
                        .and()
                    .httpBasic();
        }
    }
	
	@Configuration
    @Order(2)
    public static class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
		
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
   
	@Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
	     
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(customUserDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
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
		.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .headers().frameOptions().sameOrigin() //To enable H2 Database
        .and()
        .authorizeRequests()
        .antMatchers(
                "/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        ).permitAll()
        .antMatchers(SIGN_UP_URLS).permitAll()
        //.antMatchers(GET_URLS).permitAll()
        .antMatchers(H2_URL).permitAll()
        .anyRequest().authenticated();
        //.and()
        //.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint());;	
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
}
	
}
	
}
	
	
