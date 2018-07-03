package com.arslanyakup.jwtdemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.arslanyakup.jwtdemo.exception.CustomException;
import com.arslanyakup.jwtdemo.repository.UserRepository;
import com.arslanyakup.jwtdemo.security.JwtTokenProvider;
import com.arslanyakup.jwtdemo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public String signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getUserRoles());
		} catch (Exception e) {
			throw new CustomException("Invalid username or password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	public List<String> userRoles() {
		List<String> userRoles = new ArrayList<>();
		for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			userRoles.add(authority.getAuthority());
		}
		return userRoles;
	}

}
