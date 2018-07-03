package com.arslanyakup.jwtdemo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arslanyakup.jwtdemo.security.JwtTokenFilterConfigurer;
import com.arslanyakup.jwtdemo.security.JwtTokenProvider;
import com.arslanyakup.jwtdemo.service.UserSecurityService;
import com.arslanyakup.jwtdemo.util.SecurityUtility;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	@Qualifier("jwtTokenProvider")
	private JwtTokenProvider jwtTokenProvider;

	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHES = { "/oauth/login" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().accessDeniedPage("/oauth/login").and().authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll().anyRequest().authenticated().and().apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
		super.configure(http);
	}

	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
