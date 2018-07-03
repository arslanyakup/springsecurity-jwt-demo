package com.arslanyakup.jwtdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProvider) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}

	public JwtTokenFilterConfigurer() {
		super();
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		JwtTokenFilter filter = new JwtTokenFilter(jwtTokenProvider);
		httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

}
