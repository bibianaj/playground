package com.play.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.play.filters.JwtRequestFilter;
import com.play.service.user.BibiUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BibiUserDetailsService bibiUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(bibiUserDetailsService);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().disable()
//		.csrf().disable().authorizeRequests()
//		.antMatchers(HttpMethod.GET,
//				"/").permitAll()
//		.antMatchers(HttpMethod.POST,
//				"/performLogin").permitAll()
//		.antMatchers("/signup").permitAll()
//		.anyRequest().authenticated().and().sessionManagement().maximumSessions(1).expiredUrl("/login");
//		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
		http.csrf().disable()
		.authorizeRequests().antMatchers(
				"/authenticate")
		.permitAll().anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager authenticationMangerBean() throws Exception {
		return super.authenticationManagerBean();
	}
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new Http403ForbiddenEntryPoint();
	}
}
